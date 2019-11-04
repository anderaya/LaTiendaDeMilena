package com.app.latiendademilena;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class detallesproducto extends AppCompatActivity {

    ImageView regresar;
    Button eliminar, ok;
    productoss result=new productoss();
    String info=result.seleccionado;
    TextView nombrep;
    EditText precio,unidades,detalles;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallesproducto);

        regresar = (ImageView) findViewById(R.id.imageView14);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        eliminar = (Button) findViewById(R.id.button6);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ok = (Button) findViewById(R.id.button8);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();

            }
        });

        nombrep=(TextView)findViewById(R.id.textView26);
        precio=(EditText)findViewById(R.id.editText2);
        unidades=(EditText)findViewById(R.id.editText5);
        detalles=(EditText)findViewById(R.id.editText7);

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
                            nombrep.setText("Nombre: "+nombre);
                            precio.setText("Precio: "+ds.child("precio").getValue().toString());
                            unidades.setText("Unidades: "+ds.child("unidades").getValue().toString());
                            detalles.setText("Detalles: "+ds.child("detalles").getValue().toString());


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


    String Nombre,Detalles;
    Double Precio,Unidades;


    public void guardar(){

        Nombre=nombrep.getText().toString().trim();
        Detalles= detalles.getText().toString().trim();
        Precio= Double.parseDouble((precio.getText().toString()));
        Unidades=Double.parseDouble((unidades.getText().toString()));

        databaseReference.child("producto").child(info).child("precio").setValue(Precio);
        databaseReference.child("producto").child(info).child("unidades").setValue(unidades);
        databaseReference.child("producto").child(info).child("detalles").setValue(Detalles);

        finish();
        Intent intent = new Intent(getApplicationContext(), detallesproducto.class);
        startActivity(intent);



    }

}
