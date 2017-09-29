package com.zaita.aliyounes.gsbvc2017.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zaita.aliyounes.gsbvc2017.R;



public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }
    public void sign_in_button(View view) {
        Intent intent = new Intent(IntroActivity.this , LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void register(View view) {
        Intent intent = new Intent(IntroActivity.this , RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    public void facebook(View view) {
        String url = "https://m.facebook.com/CnamLiban/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void twitter(View view) {
        String url = "https://mobile.twitter.com/ISSAECnamLiban";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void googlePlus(View view) {
        String url = "https://plus.google.com/u/0/+ISAECnamLiban";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}



