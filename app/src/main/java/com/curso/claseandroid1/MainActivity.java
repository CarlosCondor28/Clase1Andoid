package com.curso.claseandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Campos
    Clase1 clase1;
    Button boton;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boton = findViewById(R.id.buttonn);
        textView = findViewById(R.id.textView);


        String cadena = "Hola";
        int j = 10;
        clase1 = new Clase1(cadena);

        int a= 2;
        int b = 5;
        final int c = clase1.suma(a,b);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pasar de main activity a activity2

                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                startActivity(intent);

                //Imprime un mensaje en la consola
                Log.d("Intent", "Cambio de Activity");

                // Muestra mensaje en aplicación
                Toast.makeText(getBaseContext(), "Activity2", Toast.LENGTH_SHORT).show();

                finish();

            }

        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show();

    }
}