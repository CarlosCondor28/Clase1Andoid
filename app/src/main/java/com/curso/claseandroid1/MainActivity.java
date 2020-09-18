package com.curso.claseandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Campos
    Button boton;
    EditText usuario;
    EditText contraseña;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayout);

        //Inicializar Campos
        boton = findViewById(R.id.iniciar_sesion);
        usuario = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.contraseña);
        checkBox  = findViewById(R.id.checkBox);

/*
        String cadena = "Hola";
        int j = 10;
        clase1 = new Clase1(cadena);

        int a= 2;
        int b = 5;
        final int c = clase1.suma(a,b);
        */


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = usuario.getText().toString();
                int password = Integer.parseInt(contraseña.getText().toString());

                Log.d("Usuario", user);
                Log.d("Contraseña", Integer.toString(password));


                if(user.equals("Carlos") &&  password == 12345){

                    //Pasar de main activity a activity2
                    boolean boolCheckBox = checkBox.isChecked();

                    Log.d("MainAcitivyValorBool", String.valueOf(boolCheckBox));


                    SharedPreferences.Editor sharedPreferences = getSharedPreferences("Preferencias", MODE_PRIVATE).edit();
                        sharedPreferences.putBoolean("Recuerdame",boolCheckBox);
                        sharedPreferences.apply();

                        Intent intent = new Intent(getApplicationContext(), Activity2.class);
                        String cadenaIntent = "Activy2";
                        intent.putExtra("cadena",cadenaIntent);
                        startActivity(intent);

                        //Imprime un mensaje en la consola
                        Log.d("Intent", "Cambio de Activity");

                        // Muestra mensaje en aplicación
                        Toast.makeText(getBaseContext(), "Activity2", Toast.LENGTH_SHORT).show();

                        finish();
                }

                else{
                    Toast.makeText(getBaseContext(), "Contraseña o Usuario incorrecto", Toast.LENGTH_SHORT).show();

                }

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