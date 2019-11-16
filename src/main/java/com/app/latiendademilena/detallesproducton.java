package com.app.latiendademilena;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class detallesproducton extends AppCompatActivity {
    ImageView regresar,imagenproducto;
    TextView Nombre,precio,unidades,detalles;
    productoss result=new productoss();
    String info=result.seleccionado;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallesproducton);

        regresar = (ImageView) findViewById(R.id.imageView32);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imagenproducto=(ImageView)findViewById(R.id.imageView33);

        Nombre=(TextView)findViewById(R.id.textView46);
        precio=(TextView)findViewById(R.id.textView59);
        unidades=(TextView)findViewById(R.id.textView60);
        detalles=(TextView)findViewById(R.id.textView62);

        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("producto").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombre=null;

                if(dataSnapshot.exists()){

                    dataSnapshot.getChildren();


                    for(DataSnapshot ds:dataSnapshot.getChildren()){


                        //mejorar para obtener multiples resultados
                        if(ds.getKey().equals(info)) {
                            nombre=ds.child("nombre").getValue().toString();
                            Nombre.setText("Nombre: "+nombre);
                            precio.setText("Precio: "+ds.child("precio").getValue().toString());
                            unidades.setText("Unidades: "+ds.child("unidades").getValue().toString());
                            detalles.setText("Detalles: "+ds.child("detalles").getValue().toString());
                            if(ds.child("nombreimagen").getValue().toString()!="") {
                                Glide.with(detallesproducton.this)
                                        .load(ds.child("nombreimagen").getValue().toString())
                                        .into(imagenproducto);
                            }

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
