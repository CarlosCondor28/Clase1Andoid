package com.curso.claseandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        textView = findViewById(R.id.textView2);
        Intent intent = getIntent();
        String cadenaRecuperada = intent.getStringExtra("cadena");

        textView.setText(cadenaRecuperada);

    }
}