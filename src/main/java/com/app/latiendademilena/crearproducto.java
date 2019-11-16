package com.app.latiendademilena;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class crearproducto extends AppCompatActivity {

    ImageView regresar;
    Button crear,imagenn;
    EditText nombre,precio,unidades,detalles;

    private DatabaseReference databaseReference;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef;

    private final int GALERY=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearproducto);
        // referencia de almacenamiento - permite subir ,descargar o borrar archivos
        //storageRef = storage.getReference();
        storageRef = storage.getReference().child("imagenes");

        databaseReference= FirebaseDatabase.getInstance().getReference();
        regresar = (ImageView) findViewById(R.id.imageView13);



        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        crear = (Button) findViewById(R.id.button5);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearp();
            }
        });

        imagenn = (Button) findViewById(R.id.button4);


        nombre =(EditText)findViewById(R.id.nombretext);
        precio =(EditText)findViewById(R.id.editText11);
        unidades =(EditText)findViewById(R.id.editText10);
        detalles =(EditText)findViewById(R.id.editText);
        Precio=0.0;
        Unidades=0.0;



    }

    public String Nombre,Detalles;
    Double  Precio,Unidades;
    producto produ;


    private void crearp(){
        Nombre=nombre.getText().toString().trim();
        Detalles= detalles.getText().toString().trim();
        Precio= Double.parseDouble((precio.getText().toString()));
        Unidades=Double.parseDouble((unidades.getText().toString()));

        if(TextUtils.isEmpty(Nombre)){
            Toast.makeText(this,"Se debe ingresar un nombre",Toast.LENGTH_LONG).show();
            return;
        }
        if(Precio<=0){
            Toast.makeText(this,"Se debe ingresar un precio",Toast.LENGTH_LONG).show();
            return;
        }
        if(Unidades<=0){
            Toast.makeText(this,"Se debe ingresar el # de unidades",Toast.LENGTH_LONG).show();
            return;
        }


        String id=databaseReference.push().getKey();
        produ=new producto(Nombre,Detalles,Precio,Unidades,nombreimagen);
        databaseReference.child("producto").child(id).setValue(produ);
        Toast.makeText(crearproducto.this,"Se ha creado un producto ",Toast.LENGTH_LONG).show();

        finish();
        Intent intent = new Intent(getApplicationContext(), crearproducto.class);
        startActivity(intent);


    }


    String nombreimagen="";
    Uri download;
    StorageReference file;
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){ ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    super.onActivityResult(requestCode,resultCode,data);

            if(requestCode==GALERY && resultCode==RESULT_OK) {
                Uri uri = data.getData();

                nombreimagen="image"+uri.getLastPathSegment();
                file=storageRef.child(nombreimagen);
                //StorageReference file = storageRef.child(uri.getLastPathSegment());
                UploadTask uploadTask = file.putFile(uri);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return file.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            System.out.println("Upload " + downloadUri);
                            imagenn.setText("Imagen cargada");
                            if (downloadUri != null) {

                                nombreimagen = downloadUri.toString(); //YOU WILL GET THE DOWNLOAD URL HERE !!!!


                            }

                        } else {
                            imagenn.setText("Ha ocurrido un error");
                        }
                    }
                });

            }


    }

    public void oncllick(View view) {

        Intent intent= new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,GALERY);
    }
}
