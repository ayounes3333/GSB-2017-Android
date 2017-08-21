package com.zaita.aliyounes.gsbvc2017.activities;

import android.content.Intent;
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
}



