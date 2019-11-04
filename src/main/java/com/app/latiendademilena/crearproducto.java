package com.app.latiendademilena;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Integer.parseInt;

public class crearproducto extends AppCompatActivity {

    ImageView regresar;
    Button crear;
    EditText nombre,precio,unidades,detalles;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearproducto);


        databaseReference= FirebaseDatabase.getInstance().getReference();
        regresar = (ImageView) findViewById(R.id.imageView13);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        crear = (Button) findViewById(R.id.button5);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearp();
            }
        });

        nombre =(EditText)findViewById(R.id.nombretext);
        precio =(EditText)findViewById(R.id.editText11);
        unidades =(EditText)findViewById(R.id.editText10);
        detalles =(EditText)findViewById(R.id.editText);
        Precio=0.0;
        Unidades=0.0;
    }

    public String Nombre,Detalles;
    Double  Precio,Unidades;
    producto produ;


    private void crearp(){
        Nombre=nombre.getText().toString().trim();
        Detalles= detalles.getText().toString().trim();
        Precio= Double.parseDouble((precio.getText().toString()));
        Unidades=Double.parseDouble((unidades.getText().toString()));

        if(TextUtils.isEmpty(Nombre)){
            Toast.makeText(this,"Se debe ingresar un nombre",Toast.LENGTH_LONG).show();
            return;
        }
        if(Precio<=0){
            Toast.makeText(this,"Se debe ingresar un precio",Toast.LENGTH_LONG).show();
            return;
        }
        if(Unidades<=0){
            Toast.makeText(this,"Se debe ingresar el # de unidades",Toast.LENGTH_LONG).show();
            return;
        }


        String id=databaseReference.push().getKey();
        produ=new producto(Nombre,Detalles,Precio,Unidades);
        databaseReference.child("producto").child(id).setValue(produ);
        Toast.makeText(crearproducto.this,"Se ha creado un producto ",Toast.LENGTH_LONG).show();

        finish();
        Intent intent = new Intent(getApplicationContext(), crearproducto.class);
        startActivity(intent);


    }


}
