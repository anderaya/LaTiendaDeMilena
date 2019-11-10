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

public class verpedidos extends AppCompatActivity {

    ImageView regresar, pedido1, pedido2, pedido3,listo1,listo2,listo3;
    private DatabaseReference databaseReference;
    TextView usuario1,usuario2,usuario3;
    int contador=0;
    String key1,key2,key3;
    static String seleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verpedidos);

        regresar = (ImageView) findViewById(R.id.imageView26);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pedido1 = (ImageView) findViewById(R.id.imageView28);
        pedido1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador>0) {
                    seleccionado=key1;
                    Intent intent = new Intent(getApplicationContext(), detallespedido.class);
                    startActivity(intent);
                }
            }
        });

        pedido2 = (ImageView) findViewById(R.id.imageView29);
        pedido2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador>1) {
                    seleccionado=key2;
                    Intent intent = new Intent(getApplicationContext(), detallespedido.class);
                    startActivity(intent);
                }
            }
        });

        pedido3 = (ImageView) findViewById(R.id.imageView30);
        pedido3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador==3) {
                    seleccionado=key3;
                    Intent intent = new Intent(getApplicationContext(), detallespedido.class);
                    startActivity(intent);
                }
            }
        });

        usuario1=(TextView)findViewById(R.id.textView54);
        usuario2=(TextView)findViewById(R.id.textView34);
        usuario3=(TextView)findViewById(R.id.textView33);


        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("pedido").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    dataSnapshot.getChildren();

                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        if(contador==2){
                            usuario3.setText(ds.child("usuario").getValue().toString());
                            key3=ds.getKey().toString();
                            contador++;
                        }


                        if(contador==1){
                            usuario2.setText(ds.child("usuario").getValue().toString());
                            key2=ds.getKey().toString();
                            contador++;
                        }

                        if(contador==0){

                            usuario1.setText(ds.child("usuario").getValue().toString());
                            key1=ds.getKey().toString();

                            contador++;
                        }

                    }
                    //String nombre=dataSnapshot.child("titulo").getValue().toString();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        //inicializar imagenes para dar como completado un pedido
        listo1=(ImageView)findViewById(R.id.imageView27);
        listo2=(ImageView)findViewById(R.id.imageView25);
        listo3=(ImageView)findViewById(R.id.imageView24);

        listo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //eliminar el pedido de la base de datos
                databaseReference.child("pedido").child(key1).removeValue();
                usuario1.setText("");
                finish();
                finish();

                Toast.makeText(verpedidos.this,"Se ha completado el pedido",Toast.LENGTH_LONG).show();

            }
        });

        listo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //eliminar el pedido de la base de datos
                databaseReference.child("pedido").child(key2).removeValue();
                usuario2.setText("");
                finish();
                finish();

                Toast.makeText(verpedidos.this,"Se ha completado el pedido",Toast.LENGTH_LONG).show();

            }
        });

        listo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //eliminar el pedido de la base de datos
                databaseReference.child("pedido").child(key3).removeValue();
                usuario3.setText("");
                finish();
                finish();

                Toast.makeText(verpedidos.this,"Se ha completado el pedido",Toast.LENGTH_LONG).show();

            }
        });



    }
}
