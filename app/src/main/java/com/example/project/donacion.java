package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donacion extends AppCompatActivity {

    EditText nombre;
    EditText cantidad;
    Button btnInsert;

    DatabaseReference donacionesDBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donacion);

        nombre = findViewById(R.id.txtNombre);
        cantidad = findViewById(R.id.txtCant);
        btnInsert = findViewById(R.id.Confirmar);

        donacionesDBRef = FirebaseDatabase.getInstance().getReference().child("Donaciones");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }

    private void insertData() {
        String name = nombre.getText().toString();
        Integer cant = Integer.valueOf(cantidad.getText().toString());

        donacionGetter Donacion = new donacionGetter(name, cant);

        donacionesDBRef.push().setValue(Donacion);

        Toast.makeText(com.example.project.Donacion.this, "Donacion recibida", Toast.LENGTH_SHORT).show();
    }
}