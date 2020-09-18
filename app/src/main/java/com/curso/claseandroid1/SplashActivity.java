package com.curso.claseandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Crar intent para pasar a una activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}