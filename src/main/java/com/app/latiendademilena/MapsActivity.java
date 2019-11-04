package com.app.latiendademilena;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Double.parseDouble;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private Marker Mprueba;

    public static String tvLongi;
    public static String tvLati;
    private DatabaseReference databaseReference;
    LocationManager locationManager;
    public usuarioActivo us=new usuarioActivo();

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        count=0;

        tvLongi=us.getLongitud();
        tvLati=us.getLatitud();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        CheckPermission();
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //obtener direccion

        double latitud=parseDouble(tvLati);
        double longitud=parseDouble(tvLongi);

        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera

        LatLng tienda = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(tienda).title("Tienda").snippet("Ubicacón de la tienda").icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tienda, 10));


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);


        mMap.setMyLocationEnabled(true);



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
        Toast.makeText(MapsActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider!" + provider,
                Toast.LENGTH_SHORT).show();
    }


}