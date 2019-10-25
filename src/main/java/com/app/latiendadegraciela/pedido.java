package com.app.latiendadegraciela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class pedido extends AppCompatActivity {

    ImageView regresar;
    Button realizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        regresar=(ImageView) findViewById(R.id.imageView20);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        realizar=(Button) findViewById(R.id.button11);
        realizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //anunciar que el pedido ha sido realizado
            }
        });

    }
}
