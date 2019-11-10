package com.app.latiendademilena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class detallespedido extends AppCompatActivity {

    ImageView regresar;
    Button mapa;
    verpedidos vpedidos=new verpedidos();
    String key=vpedidos.seleccionado;
    public static Double latitud,longitud;
    private DatabaseReference databaseReference;
    TextView usuario,producto1,producto2,producto3,valortotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallespedido);

        regresar = (ImageView) findViewById(R.id.imageView31);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mapa = (Button) findViewById(R.id.button13);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        usuario=(TextView)findViewById(R.id.textView51);
        producto1=(TextView)findViewById(R.id.textView55);
        producto2=(TextView)findViewById(R.id.textView56);
        producto3=(TextView)findViewById(R.id.textView57);
        valortotal=(TextView)findViewById(R.id.textView58);

        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("pedido").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    dataSnapshot.getChildren();

                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.getKey().toString().equals(key)){
                            usuario.setText("Usuario"+ds.child("usuario").getValue().toString());
                            producto1.setText(ds.child("producto1").getValue().toString()+" unidades: "+ds.child("unidades1").getValue().toString());
                            if(!ds.child("producto2").getValue().toString().equals("")) {
                                producto2.setText(ds.child("producto2").getValue().toString() + " unidades: " + ds.child("unidades2").getValue().toString());
                            }
                            if(!ds.child("producto3").getValue().toString().equals("")) {
                                producto3.setText(ds.child("producto3").getValue().toString() + " unidades: " + ds.child("unidades3").getValue().toString());
                            }
                            valortotal.setText("Valor: "+ds.child("valor").getValue().toString());
                            latitud=Double.parseDouble(ds.child("latitud").getValue().toString());
                            longitud=Double.parseDouble(ds.child("longitud").getValue().toString());
                        }


                    }
                    //String nombre=dataSnapshot.child("titulo").getValue().toString();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}
