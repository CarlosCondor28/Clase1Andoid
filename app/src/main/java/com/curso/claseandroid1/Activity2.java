package com.curso.claseandroid1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Activity2 extends AppCompatActivity {

    //Campos
    private int codigoDeSolicitud = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private String hola = "Hola";
    private String[] arregloDeCadenas;
    private int[] arregloDeEnteros;
    private Button bPosicion;

    private LocationCallback locationCallback;
    private LocationRequest locationRequest;


    private TextView textView;
    private TextView textAltitud;
    private TextView textOrientacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        arregloDeCadenas = new String[]{"a", "b", "c"};

        bPosicion = findViewById(R.id.buttonposicion);
        textView = findViewById(R.id.textView2);
        textAltitud = findViewById(R.id.altitud);
        textOrientacion = findViewById(R.id.orientacion);

        Intent intent = getIntent();
        String cadenaRecuperada = intent.getStringExtra("cadena");

        textView.setText(cadenaRecuperada);

        //Inicializar FusedLocationClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(0);
        locationRequest.setFastestInterval(0);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                List<Location> locationList = locationResult.getLocations();

/*                for(int i = 0; i < locationList.size(); i = i + 1){
                    Location location =  locationList.get(i);
                    Log.d("CoordenadaCallBack", location.toString());

                    textView.setText(location.toString());
                }*/

                for (Location location :  locationList  ) {

                    Log.d("CoordenadaCallBack", location.toString());

                    //Agregar coordenadas a un textView
                    double lon = location.getLongitude();
                    double lat = location.getLatitude();

                    textView.setText(lon + " , " + lat);
                    textView.setTextSize(20);

                    //Agregar Altitud
                    textAltitud.setText(Double.toString(location.getAltitude()));
                    //Agregar Orientación
                    textOrientacion.setText( Float.toString(location.getBearing()));

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRefGPS = database.getReference("PosicionGPS");

                    myRefGPS.setValue( String.valueOf(lat) + " , " + String.valueOf(lon) );




                }
            }
        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            pedriPermiso();
        } else {

/*            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback,
                    null);*/

/*            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        Log.d("Coordenadas", location.toString());
                    }
                    else{
                        Log.d("Coordenadas", "Coordenas nulas");

                    }
                }
            });*/


        }

        bPosicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarUltimaPosicion();
            }
        });

        //Codigo para la BD
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Mensajes");
        DatabaseReference referenciaUserios = database.getReference("Users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child :snapshot.getChildren()){
                    Log.d(child.getKey().toString(), child.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        referenciaUserios.orderByPriority().limitToLast(2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child :snapshot.getChildren()){
                    Log.d(child.getKey().toString(), child.getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();

        // Actualización de Posiciones
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                null);

    }

    private void solicitarUltimaPosicion() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            pedriPermiso();
        } else {
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    Log.d("Coordenadas", location.toString());
                }

                else{
                    Log.d("Coordenadas", "Coordenas nulas");

                }
            }
        });
        }
    }

    private void pedriPermiso() {

        String[] permisos = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};

        ActivityCompat.requestPermissions(this,permisos,codigoDeSolicitud);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == codigoDeSolicitud && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
            Toast.makeText(this, "Permiso Permitido",Toast.LENGTH_SHORT).show();
            solicitarUltimaPosicion();
        }
    }
}