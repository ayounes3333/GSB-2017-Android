package com.zaita.aliyounes.gsbvc2017;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

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

                        Intent i = new Intent(SplashActivity.this , LoginActivity.class);
                        startActivity(i);
                    }
                }, 2000);
            }
        });
    }
}
