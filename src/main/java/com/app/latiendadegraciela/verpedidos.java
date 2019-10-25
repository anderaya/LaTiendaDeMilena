package com.app.latiendadegraciela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class verpedidos extends AppCompatActivity {

    ImageView regresar,pedido1,pedido2,pedido3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verpedidos);

        regresar=(ImageView) findViewById(R.id.imageView26);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pedido1=(ImageView) findViewById(R.id.imageView28);
        pedido1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detallespedido.class);
                startActivity(intent);
            }
        });

        pedido2=(ImageView) findViewById(R.id.imageView29);
        pedido2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detallespedido.class);
                startActivity(intent);
            }
        });

        pedido3=(ImageView) findViewById(R.id.imageView30);
        pedido3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detallespedido.class);
                startActivity(intent);
            }
        });


    }
}
