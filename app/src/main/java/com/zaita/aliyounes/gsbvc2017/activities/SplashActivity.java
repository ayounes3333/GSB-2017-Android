package com.zaita.aliyounes.gsbvc2017.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.helpers.PrefUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView img = (ImageView) findViewById(R.id.imageView_splash);
        img.post(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        if(PrefUtils.getBoolean(SplashActivity.this , PrefUtils.Prefs.IS_USER_LOGGED_IN , false)) {
                            Intent i = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Intent i = new Intent(SplashActivity.this, IntroActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }, 2000);
            }
        });
    }
}
