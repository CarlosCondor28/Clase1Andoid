package com.curso.claseandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences("Preferencias", MODE_PRIVATE);
                boolean boolCheckBox = sharedPreferences.getBoolean("Recuerdame", false);
                Log.d("valorShareP", String.valueOf(boolCheckBox));

               if(boolCheckBox){
                    //Crar intent para pasar a una activity
                    Intent intent = new Intent(SplashActivity.this, SpeechActivity.class);
                    String cadenaIntent = "Activy2";
                    intent.putExtra("cadena",cadenaIntent);
                    startActivity(intent);
                }

               else{
                    //Crar intent para pasar a una activity
                   Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                   startActivity(intent);
               }


            }
        }, 2000);
    }
}