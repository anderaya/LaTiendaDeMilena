package com.app.latiendademilena;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


public class MainActivity extends AppCompatActivity {

    Button iniciar;
    TextView registrar, recuperar;
    private FirebaseAuth mAuth;
    private EditText Contraseña,Correo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //boton iniciar
        iniciar = findViewById(R.id.button2);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarss();
            }
        });
        //boton registrar
        registrar = (TextView) findViewById(R.id.textView7);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), registrar.class);
                startActivity(intent);

            }
        });

        //boton recuperar cuenta
        recuperar = (TextView) findViewById(R.id.textView5);
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), recuperarcuenta.class);
                startActivity(intent);

            }
        });

        mAuth = FirebaseAuth.getInstance();
        //casillas para guardar datos
        Correo =(EditText)findViewById(R.id.editText3);
        Contraseña = (EditText)findViewById(R.id.editText4);

    }


    //objeto de tipo usuario activo
    private usuarioActivo us=new usuarioActivo();
    private DatabaseReference databaseReference;


    // funcion iniciar sesión
    private void iniciarss(){
        String contraseña= Contraseña.getText().toString().trim();
        String correo= Correo.getText().toString().trim();
        us.setCorreo(correo);

        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Falta ingresar un correo",Toast.LENGTH_LONG).show();
            return ;
        }
        if(TextUtils.isEmpty(contraseña)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();

            return ;
        }

        mAuth.signInWithEmailAndPassword(correo,contraseña).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"Se ha iniciado sesión",Toast.LENGTH_LONG).show();

                    FirebaseUser user = mAuth.getCurrentUser();


                    databaseReference= FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                dataSnapshot.getChildren();
                                for(DataSnapshot ds:dataSnapshot.getChildren()){
                                    //mejorar para obtener multiples resultados
                                    if(ds.child("correo").getValue().toString().equals(us.getCorreo())) {


                                        us.setLongitud(ds.child("longitud").getValue().toString());
                                        us.setLatitud(ds.child("latitud").getValue().toString());
                                        us.setTipouser(ds.child("tipouser").getValue().toString());
                                        us.setCorreo(ds.child("correo").getValue().toString());

                                        if(ds.child("tipouser").getValue().toString().equals("admn")){
                                            finish();
                                            Intent intent = new Intent(getApplicationContext(), menuadm.class);
                                            startActivity(intent);
                                        }else{
                                            finish();
                                            Intent intent = new Intent(getApplicationContext(), menu.class);
                                            startActivity(intent);
                                        }

                                        return;

                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }else{
                    Toast.makeText(MainActivity.this,"Correo o contraseña incorrecta ",Toast.LENGTH_LONG).show();

                    // Log.d(TAG, "onComplete: Failed=" + task.getException().getMessage());

                }
            }
        });

    }



}
