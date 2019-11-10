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

public class pedido extends AppCompatActivity {

    ImageView regresar;
    Button realizar;
    Listadecompras list=new Listadecompras();
    private DatabaseReference databaseReference;

    TextView producto1,producto2,producto3,unidades1,unidades2,unidades3,valort;
    ImageView img1,img2,img3;
    double valor1=0,valor2=0,valor3=0;
    int numeros=list.entrada;
    double total;
    //nombre de los productos del 1 al 3
    String p1="",p2="",p3="";

    //boleanos para evitar eliminar un lugar que ya no existe
    boolean eliminado1=false,eliminado2=false,eliminado3=false;

    //unidades de los productos
    String u1="",u2="",u3="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        //inicializar variables
        total=0;



        regresar = (ImageView) findViewById(R.id.imageView20);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        realizar = (Button) findViewById(R.id.button11);
        realizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crearpedido();
                //anunciar que el pedido ha sido realizado
            }
        });

        producto1=(TextView)findViewById(R.id.textView42);
        producto2=(TextView)findViewById(R.id.textView38);
        producto3=(TextView)findViewById(R.id.textView43);
        unidades1=(TextView)findViewById(R.id.textView39);
        unidades2=(TextView)findViewById(R.id.textView44);
        unidades3=(TextView)findViewById(R.id.textView45);
        valort=(TextView)findViewById(R.id.textView41);

        img1=(ImageView)findViewById(R.id.imageView21);
        img2=(ImageView)findViewById(R.id.imageView22);
        img3=(ImageView)findViewById(R.id.imageView23);

        //imagenes para eliminar un pedido de la lista
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !eliminado1&&list.entrada>0) {
                    list.quitar(0);
                    producto1.setText("");
                    unidades1.setText("");
                    eliminado1=true;
                    total=total-valor1;
                    valort.setText("Valor total: "+String.valueOf(total));
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !eliminado2&&list.entrada>1) {
                    list.quitar(1);
                    producto2.setText("");
                    unidades2.setText("");
                    eliminado2=true;
                    total=total-valor2;
                    valort.setText("Valor total: "+String.valueOf(total));
                }

            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !eliminado3&&list.entrada==3) {
                    list.quitar(2);
                    producto3.setText("");
                    unidades3.setText("");
                    total=total-valor3;
                    eliminado3=true;
                    valort.setText("Valor total: "+String.valueOf(total));
                }
            }
        });


        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("producto").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    dataSnapshot.getChildren();

                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        if(numeros>=1&&ds.getKey().toString().equals(list.lista[0][0])){
                            producto1.setText(ds.child("nombre").getValue().toString());
                            unidades1.setText(list.lista[0][1]);
                            valor1=Double.parseDouble(ds.child("precio").getValue().toString())*Double.parseDouble(list.lista[0][1]);
                            total= total+Double.parseDouble(ds.child("precio").getValue().toString())*Double.parseDouble(list.lista[0][1]);
                            p1=ds.child("nombre").getValue().toString();
                            u1=list.lista[0][1];
                        }

                        if(numeros>=2&&ds.getKey().toString().equals(list.lista[1][0])){
                            producto2.setText(ds.child("nombre").getValue().toString());
                            unidades2.setText(list.lista[1][1]);
                            valor2=Double.parseDouble(ds.child("precio").getValue().toString())*Double.parseDouble(list.lista[1][1]);
                            total= total+Double.parseDouble(ds.child("precio").getValue().toString())*Double.parseDouble(list.lista[1][1]);
                            p2=ds.child("nombre").getValue().toString();
                            u2=list.lista[1][1];
                        }
                        if(numeros==3&&ds.getKey().toString().equals(list.lista[2][0])){
                            producto3.setText(ds.child("nombre").getValue().toString());
                            unidades3.setText(list.lista[2][1]);
                            valor3=Double.parseDouble(ds.child("precio").getValue().toString())*Double.parseDouble(list.lista[2][1]);
                            total= total+Double.parseDouble(ds.child("precio").getValue().toString())*Double.parseDouble(list.lista[2][1]);
                            p3=ds.child("nombre").getValue().toString();
                            u3=list.lista[2][1];
                        }




                    }
                    //String nombre=dataSnapshot.child("titulo").getValue().toString();


                }
                valort.setText("Valor total: "+String.valueOf(total));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
    Objetopedido pedi;
    usuarioActivo us=new usuarioActivo();
    public void crearpedido(){

        String id=databaseReference.push().getKey();
        pedi=new Objetopedido(p1,p2,p3,u1,u2,u3,us.correo,us.latitud,us.longitud,total);
        if(numeros!=0) {
            databaseReference.child("pedido").child(id).setValue(pedi);
        }
        if(numeros==0){

        }else {
            Toast.makeText(pedido.this, "Se ha realizado el pedido ", Toast.LENGTH_LONG).show();
        }

        list.reinicia();
        list.setEntrada(0);
        finish();
        Intent intent = new Intent(getApplicationContext(), menu.class);
        startActivity(intent);


    }



}
