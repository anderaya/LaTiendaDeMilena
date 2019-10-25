package com.app.latiendadegraciela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class menuadm extends AppCompatActivity {

    TextView crear,productos,pedidos,cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuadm);

        crear=(TextView) findViewById(R.id.textView10);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),crearproducto.class);
                startActivity(intent);
            }
        });

        productos = (TextView) findViewById(R.id.textView15);
        productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), buscar.class);
                startActivity(intent);
            }
        });


        pedidos=(TextView) findViewById(R.id.textView16);
        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),verpedidos.class);
                startActivity(intent);
            }
        });

        cerrar=(TextView) findViewById(R.id.textView17);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
