package com.zaita.aliyounes.gsbvc2017.network;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.network.cookies.AddCookiesInterceptor;
import com.zaita.aliyounes.gsbvc2017.network.cookies.ReceivedCookiesInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

public class ServiceGenerator {

    public static final String API_BASE_URL = UrlManager.BASE_URL_API;
    private static final String CACHE_CONTROL = "Cache-Control";

    // create an instance of OkLogInterceptor using a builder()
//    private static OkLogInterceptor okLogInterceptor = OkLogInterceptor.builder().build();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS);

    private static Gson gson = new GsonBuilder()
//            .registerTypeAdapter(com.torch.agora.android.model.Request.class, new RequestGsonAdapter())
//            .registerTypeAdapter(RequestListGsonAdapter.getRequestListType(),
//                    RequestListGsonAdapter.create())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.ssZ")
            .create();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
    private static Retrofit retrofit;

    public static Gson getGson() {
        return gson;
    }

    public static void setup() {

        httpClient.addInterceptor(provideOfflineCacheInterceptor());
        httpClient.addInterceptor(new AddCookiesInterceptor()); // VERY VERY IMPORTANT
        httpClient.addInterceptor(new ReceivedCookiesInterceptor()); // VERY VERY IMPORTANT
        httpClient.addInterceptor(provideHttpLoggingInterceptor());
        httpClient.addNetworkInterceptor(new StethoInterceptor());// Stetho
        //httpClient.addNetworkInterceptor(provideCacheInterceptor());
        httpClient.cache(provideCache());
        OkHttpClient client = httpClient.build();
        retrofit = builder.client(client).build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        if (authToken != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", authToken)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }
        return retrofit.create(serviceClass);
    }

    public static Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(2, TimeUnit.MINUTES)
                        .build();

                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    public static Interceptor provideOfflineCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (!GSBApplication.hasNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    private static Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(GSBApplication.getInstance().getCacheDir(), "http-cache"),
                    10 * 1024 * 1024); // 10 MB
        } catch (Exception e) {
            Timber.e(e, "Could not create Cache!");
        }
        return cache;
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Timber.d(message);
                    }
                });
        httpLoggingInterceptor.setLevel(true ? HEADERS : NONE);
        return httpLoggingInterceptor;
    }
}

