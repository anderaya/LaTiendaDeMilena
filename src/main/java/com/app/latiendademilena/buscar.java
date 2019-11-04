package com.app.latiendademilena;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class buscar extends AppCompatActivity {

    ImageView regresar;
    Button buscar;
    EditText nombreproducto;
    static String nombrep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        regresar = (ImageView) findViewById(R.id.imageView19);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buscar = (Button) findViewById(R.id.button10);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBuscar();
            }
        });
        nombreproducto=findViewById(R.id.editText6);
    }

    public void setBuscar(){

        nombrep= nombreproducto.getText().toString().trim();

        if(nombrep.isEmpty()){
            Toast.makeText(buscar.this,"Es necesario proporcionar el nombre del producto ",Toast.LENGTH_LONG).show();

        }else{
            Intent intent = new Intent(getApplicationContext(),productoss.class);
            startActivity(intent);

        }


    }

}
