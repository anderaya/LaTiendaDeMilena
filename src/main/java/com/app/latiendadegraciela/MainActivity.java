package com.app.latiendadegraciela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button iniciar;
    TextView registrar,recuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iniciar=findViewById(R.id.button2);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);

            }
        });

        registrar=(TextView)findViewById(R.id.textView7);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),registrar.class);
                startActivity(intent);

            }
        });

        recuperar=(TextView)findViewById(R.id.textView5);
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),recuperarcuenta.class);
                startActivity(intent);

            }
        });

    }
}
