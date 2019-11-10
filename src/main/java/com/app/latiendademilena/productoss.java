package com.app.latiendademilena;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class productoss extends AppCompatActivity {

    ImageView regresar, producto1, producto2;
    Button confirmar;

    TextView Nombre,precio,nombre2,precio2;
    EditText unidades,unidades2;
    Listadecompras list;
    usuarioActivo us=new usuarioActivo();

    int contador;

    private DatabaseReference databaseReference;
    buscar bl=new buscar();


    String id1,id2;
    static String seleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productoss);
        seleccionado="";
        regresar = (ImageView) findViewById(R.id.imageView16);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirmar = (Button) findViewById(R.id.button9);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregar();

            }
        });

        producto1 = (ImageView) findViewById(R.id.imageView17);
        producto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //para usuario normal
                if(us.getTipouser().equals("admn")){
                    Intent intent = new Intent(getApplicationContext(), detallesproducto.class);
                    startActivity(intent);
                    seleccionado = id1;
                }else {
                    Intent intent = new Intent(getApplicationContext(), detallesproducton.class);
                    startActivity(intent);
                    seleccionado = id1;
                }

            }
        });

        producto2 = (ImageView) findViewById(R.id.imageView18);
        producto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(us.getTipouser().equals("admn")){
                    Intent intent = new Intent(getApplicationContext(), detallesproducto.class);
                    startActivity(intent);
                    seleccionado = id2;
                }else {
                    Intent intent = new Intent(getApplicationContext(), detallesproducton.class);
                    startActivity(intent);
                    seleccionado = id2;
                }

            }
        });



        Nombre=(TextView)findViewById(R.id.textView27);
        precio=(TextView)findViewById(R.id.textView28);
        nombre2=(TextView)findViewById(R.id.textView29);
        precio2=(TextView)findViewById(R.id.textView30);

        unidades=(EditText)findViewById(R.id.editText8);
        unidades2=(EditText)findViewById(R.id.editText9);
        contador=0;

        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("producto").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombre=null;

                if(dataSnapshot.exists()){

                    dataSnapshot.getChildren();


                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("nombre").getValue().toString().equals(bl.nombrep)&&contador==1){
                            nombre=ds.child("nombre").getValue().toString();
                            nombre2.setText(nombre);
                            precio2.setText("$ "+ds.child("precio").getValue().toString());
                            id2=ds.getKey().toString();
                            return;
                        }

                        //mejorar para obtener multiples resultados
                        if(ds.child("nombre").getValue().toString().equals(bl.nombrep)&&contador==0) {
                            nombre=ds.child("nombre").getValue().toString();
                            Nombre.setText(nombre);
                            precio.setText("$ "+ds.child("precio").getValue().toString());
                            id1=ds.getKey().toString();
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

        u1=0.0;
        u2=0.0;


    }
    Double u1,u2;

    public void agregar(){
        u1=Double.parseDouble((unidades.getText().toString()));
        u2=Double.parseDouble((unidades2.getText().toString()));
        list=new Listadecompras();

        if(u1<=0&&u2<=0){
            Toast.makeText(this,"Se debe ingresar el # de unidades",Toast.LENGTH_LONG).show();
            return;
        }else if(contador==1&&u1>0){
            if(list.entrada==3){
                Toast.makeText(this,"Pedido lleno, maximo 3 productos",Toast.LENGTH_LONG).show();
            }else {
                //agregar al pedido
                list.agregar(id1, String.valueOf(u1));
                Toast.makeText(this,"Producto agregado al pedido",Toast.LENGTH_LONG).show();

            }

        } else if(contador==2&&u2>0){
            if(list.entrada==3){
                Toast.makeText(this,"Pedido lleno, maximo 3 productos",Toast.LENGTH_LONG).show();
            }else {
                //agregar al pedido
                list.agregar(id2, String.valueOf(u2));
                Toast.makeText(this,"Producto agregado al pedido",Toast.LENGTH_LONG).show();
            }

        }



        // falta hacer la distinción entre administrador o usuario normal.

        finish();
        Intent intent = new Intent(getApplicationContext(), productoss.class);
        startActivity(intent);
    }
}
