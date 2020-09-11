package com.curso.claseandroid1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Clase1 clase1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String cadena = "Hola";
        int j = 10;
        clase1 = new Clase1(cadena);

        int a= 2;
        int b = 5;
        int c = clase1.suma(a,b);

        Log.d("TagSuma", Integer.toString(c));

    }
}