package com.app.latiendademilena;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class productoss extends AppCompatActivity {

    ImageView regresar, producto1, producto2;
    Button confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productoss);

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
                finish();
                Intent intent = new Intent(getApplicationContext(), productoss.class);
                startActivity(intent);
            }
        });

        producto1 = (ImageView) findViewById(R.id.imageView17);
        producto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //para usuario normal
                Intent intent = new Intent(getApplicationContext(), detallesproducton.class);
                startActivity(intent);
            }
        });

        producto2 = (ImageView) findViewById(R.id.imageView18);
        producto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), detallesproducton.class);
                startActivity(intent);
            }
        });

    }
}
