package com.curso.claseandroid1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
                textView.setText(Integer.toString(c));

                //Imprime un mensaje en la consola
                Log.d("TagSuma", Integer.toString(c));

                // Muestra mensaje en aplicación
                Toast.makeText(getBaseContext(), "OnCreate", Toast.LENGTH_SHORT).show();

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