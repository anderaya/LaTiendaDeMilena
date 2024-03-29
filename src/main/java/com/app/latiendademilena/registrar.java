package com.app.latiendademilena;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class registrar extends AppCompatActivity implements  LocationListener{

    ImageView regresar;
    Button registrar,ingresarubicaciones;
    private FirebaseAuth mAuth;
    private EditText  Contraseña,Correo;

    LocationManager locationManager;

    public String contraseña,correo;
    public static String tvLongi,tvLati;
    public int selecionado;

    public usuarioActivo us=new usuarioActivo();
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        mAuth = FirebaseAuth.getInstance();
        selecionado=0;

        databaseReference=FirebaseDatabase.getInstance().getReference();
        regresar = (ImageView) findViewById(R.id.imageView);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        registrar = (Button) findViewById(R.id.button3);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrarUsuario();

            }
        });

        ingresarubicaciones=(Button)findViewById(R.id.button);
        ingresarubicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ingresarubicaciones.setText("Ubicación agregada");
                    selecionado = 1;


            }
        });

        mAuth = FirebaseAuth.getInstance();

        Contraseña =(EditText)findViewById(R.id.contraseñatext);
        Correo =(EditText)findViewById(R.id.correotext);
        CheckPermission();

    }

    private void registrarUsuario(){
        correo=Correo.getText().toString().trim();
        contraseña= Contraseña.getText().toString().trim();

        us.setCorreo(correo);

        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Se debe ingresar un correo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(contraseña)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }else if(contraseña.length()<8){
            Toast.makeText(this,"La contraseña debe tener por lo menos 8 caracteres",Toast.LENGTH_LONG).show();
            return;
        }

        if(selecionado!=1){
            Toast.makeText(this,"Falta agregar la ubicación",Toast.LENGTH_LONG).show();

            return;
        }

        //crear un usuario
        //creating a new user


        mAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            setRegistrarUsuario(correo,tvLati,tvLongi);
                            finish();
                            Intent intent = new Intent(getApplicationContext(), menu.class);
                            startActivity(intent);





                        }else{
                            Toast.makeText(registrar.this,"Datos incorrectos ",Toast.LENGTH_LONG).show();

                        }

                    }
                });




    }

    Usuario user;
    //Para Almacenar os datos en un usuario activo
    public void setRegistrarUsuario(String correo, String latitud, String longitud){
        String tipo="normal";
        String id=databaseReference.push().getKey();
        user=new Usuario(correo,longitud,latitud,tipo);
        databaseReference.child("usuario").child(id).setValue(user);
        Toast.makeText(registrar.this,"Se ha registrado el usuario ",Toast.LENGTH_LONG).show();

        //Almacenar os datos en un usuario activo
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("usuario").addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    dataSnapshot.getChildren();

                    for(DataSnapshot ds:dataSnapshot.getChildren()){

                        //mejorar para obtener multiples resultados
                        if(ds.child("correo").getValue().toString().equals(us.getCorreo())) {
                            us.setCorreo(ds.child("correo").getValue().toString());
                            us.setLongitud(ds.child("longitud").getValue().toString());
                            us.setLatitud(ds.child("latitud").getValue().toString());
                            us.setTipouser(ds.child("tipouser").getValue().toString());


                            return;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void onResume() {
        super.onResume();
        getLocation();
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void CheckPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // Getting reference to TextView tv_longitude

        tvLongi = String.valueOf(location.getLongitude());
        tvLati = String.valueOf(location.getLatitude());

    }


    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(registrar.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider!" + provider,
                Toast.LENGTH_SHORT).show();
    }


    //obtener ubicacion



}
