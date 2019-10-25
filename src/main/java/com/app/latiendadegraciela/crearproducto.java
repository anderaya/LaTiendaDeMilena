package com.app.latiendadegraciela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class crearproducto extends AppCompatActivity {

    ImageView regresar;
    Button crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearproducto);

        regresar=(ImageView) findViewById(R.id.imageView13);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        crear=(Button) findViewById(R.id.button5);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(),crearproducto.class);
                startActivity(intent);
            }
        });
    }
}
