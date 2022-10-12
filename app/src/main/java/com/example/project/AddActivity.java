package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText nombre,CantidadAct,CantidadDes,tulr;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nombre = (EditText)findViewById(R.id.txtName);
        CantidadAct = (EditText) findViewById(R.id.txtAct);
        CantidadDes = (EditText) findViewById(R.id.txtDese);
        tulr = (EditText) findViewById(R.id.txtImage);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void insertData(){
        Map<String, Object>map = new HashMap<>();
        map.put("nombre",nombre.getText().toString());
        map.put("CantidadAct", CantidadAct.getText().toString());
        map.put("CantidadDes",CantidadDes.getText().toString());
        map.put("tulr",tulr.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Inventario").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Datos insertados Correctamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearAll(){
        nombre.setText("");
        CantidadDes.setText("");
        CantidadAct.setText("");
        tulr.setText("");
    }
}