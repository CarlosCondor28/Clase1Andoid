package com.curso.claseandroid1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Activity2 extends AppCompatActivity {

    //Campos
    private int codigoDeSolicitud = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private String hola= "Hola";
    private String[] arregloDeCadenas;
    private int[] arregloDeEnteros;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        arregloDeCadenas = new String[]{ "a", "b", "c"};

        textView = findViewById(R.id.textView2);
        Intent intent = getIntent();
        String cadenaRecuperada = intent.getStringExtra("cadena");

        textView.setText(cadenaRecuperada);

        //Inicializar FusedLocationClient

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            pedriPermiso();
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.d("Coordenadas", location.toString());
                        }
                    }
                });


    }

    private void pedriPermiso() {

        String[] permisos = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};

        ActivityCompat.requestPermissions(this,permisos,codigoDeSolicitud);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == codigoDeSolicitud && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permiso Permitido",Toast.LENGTH_SHORT).show();
        }
    }
}