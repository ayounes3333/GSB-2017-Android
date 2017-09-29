package com.zaita.aliyounes.gsbvc2017.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.helpers.PrefUtils;
import com.zaita.aliyounes.gsbvc2017.network.apis.UserNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.User;

import java.io.IOException;
import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends AppCompatActivity {

    private EditText editText_username;
    private EditText editText_password;
    private View loginForm;
    private View progressView;
    private CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        compositeDisposable = new CompositeDisposable();
        setupViews();
    }
    private void setupViews() {
        loginForm = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_username = (EditText) findViewById(R.id.editText_email);
        findViewById(R.id.button_signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editText_username.getText().toString().equalsIgnoreCase("") && !editText_password.getText().toString().equalsIgnoreCase("")) {
                    progressView.setVisibility(View.VISIBLE);
                    loginForm.setVisibility(View.GONE);
                    UserNetworkCalls.loginUser(editText_username.getText().toString() , editText_password.getText().toString()).subscribe(new Observer<User>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(User value) {
                            loginForm.setVisibility(View.VISIBLE);
                            progressView.setVisibility(View.GONE);
                            Log.d("Login Successful" , value.getUsrFullname());
                            PrefUtils.setBoolean(LoginActivity.this , PrefUtils.Prefs.IS_USER_LOGGED_IN , true);
                            Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressView.setVisibility(View.GONE);
                            loginForm.setVisibility(View.VISIBLE);
                            Log.e("Login" , "Error Logging In" , e);
                            if(e instanceof SocketException || e instanceof IOException) {
                                Toast.makeText(LoginActivity.this , R.string.no_internet , Toast.LENGTH_SHORT).show();
                            } else if (e instanceof Exception) {
                                Toast.makeText(LoginActivity.this , e.getMessage() , Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
