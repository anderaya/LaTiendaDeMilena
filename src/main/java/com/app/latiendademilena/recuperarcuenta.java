package com.app.latiendademilena;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class recuperarcuenta extends AppCompatActivity {

    ImageView regresar;
    Button confirmar;

    private FirebaseAuth mAuth;
    EditText correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperarcuenta);
        mAuth = FirebaseAuth.getInstance();


        regresar = findViewById(R.id.imageView3);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirmar = findViewById(R.id.button7);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reestablecerContraseña();
                finish();

            }
        });

        //inciializar campo de texto
        correo=(EditText)findViewById(R.id.editText12);

    }

    String Correo;
    public void reestablecerContraseña(){
        Correo=correo.getText().toString().trim();
        mAuth.sendPasswordResetEmail(Correo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(recuperarcuenta.this,"Se ha enviado un correo de reestablecimiento",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
