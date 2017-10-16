package com.zaita.aliyounes.gsbvc2017.helpers;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.network.apis.BranchesNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.apis.BrandsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.apis.ClientsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.apis.ProductsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.apis.SuppliersNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Branch;
import com.zaita.aliyounes.gsbvc2017.pojos.Brand;
import com.zaita.aliyounes.gsbvc2017.pojos.Client;
import com.zaita.aliyounes.gsbvc2017.pojos.Product;
import com.zaita.aliyounes.gsbvc2017.pojos.Supplier;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ali.younes at 10/16/2017.
 */

public class SpinnersHelper {
    public static void populateBranchSpinner(final Context context, final Spinner spinnerBranches , final CompositeDisposable compositeDisposable) {
        BranchesNetworkCalls.getAllBranches().subscribe(new Observer<List<Branch>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Branch> value) {
                ArrayList<String> date = new ArrayList<>();
                for (Branch branch : value) {
                    date.add(String.valueOf(branch.getCodeBr()));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, date);
                spinnerBranches.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Branches" , "Error getting branches" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(context , R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else if (e instanceof Exception) {
                    Toast.makeText(context , e.getMessage() , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public static void populateBrandSpinner(final Context context, final Spinner spinnerBrands , final CompositeDisposable compositeDisposable) {
        BrandsNetworkCalls.getAllBrands().subscribe(new Observer<List<Brand>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Brand> value) {
                ArrayList<String> date = new ArrayList<>();
                for (Brand brand : value) {
                    date.add(String.valueOf(brand.getCodeBrd()));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, date);
                spinnerBrands.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Brands" , "Error getting brands" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(context , R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else if (e instanceof Exception) {
                    Toast.makeText(context , e.getMessage() , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public static void populateClientSpinner(final Context context, final Spinner spinnerClients , final CompositeDisposable compositeDisposable) {
        ClientsNetworkCalls.getAllClients().subscribe(new Observer<List<Client>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Client> value) {
                ArrayList<String> date = new ArrayList<>();
                for (Client client : value) {
                    date.add(String.valueOf(client.getNameClt()));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, date);
                spinnerClients.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Clients" , "Error getting clients" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(context , R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else if (e instanceof Exception) {
                    Toast.makeText(context , e.getMessage() , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public static void populateProductSpinner(final Context context, final Spinner spinnerProducts , final CompositeDisposable compositeDisposable) {
        ProductsNetworkCalls.getAllProducts().subscribe(new Observer<List<Product>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Product> value) {
                ArrayList<String> date = new ArrayList<>();
                for (Product product : value) {
                    date.add(String.valueOf(product.getNamePr()));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, date);
                spinnerProducts.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Products" , "Error getting products" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(context , R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else if (e instanceof Exception) {
                    Toast.makeText(context , e.getMessage() , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public static void populateSupplierSpinner(final Context context, final Spinner spinnerSuppliers , final CompositeDisposable compositeDisposable) {
        SuppliersNetworkCalls.getAllSuppliers().subscribe(new Observer<List<Supplier>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Supplier> value) {
                ArrayList<String> date = new ArrayList<>();
                for (Supplier supplier : value) {
                    date.add(String.valueOf(supplier.getNameSup()));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, date);
                spinnerSuppliers.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Get Suppliers" , "Error getting suppliers" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(context , R.string.no_internet , Toast.LENGTH_SHORT).show();
                } else if (e instanceof Exception) {
                    Toast.makeText(context , e.getMessage() , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
