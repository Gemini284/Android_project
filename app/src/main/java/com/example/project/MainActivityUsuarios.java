package com.example.project;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.adapter.AdapterUsuario;
import com.example.project.pojo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivityUsuarios extends AppCompatActivity {

    Button siguiente;

        DatabaseReference ref;
        ArrayList<Usuario> list;
        RecyclerView rv;
        SearchView searchView;
        AdapterUsuario adapter;
        LinearLayoutManager lm;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_usuarios);

        siguiente = (Button) findViewById(R.id.alimentosbtton);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent siguiente = new Intent(MainActivityUsuarios.this, Activity_Organizacion.class);
                startActivity(siguiente);
            }
        });

        ref = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        rv = findViewById(R.id.rv);
        searchView = findViewById(R.id.search);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterUsuario(list);
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Usuario ms = snapshot.getValue(Usuario.class);
                        list.add(ms);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscar(s);
                return true;
            }
        });


    }

    private void buscar(String s) {
        ArrayList<Usuario>milista = new ArrayList<>();
        for (Usuario obj : list) {
            if (obj.getNombre().toLowerCase().contains(s.toLowerCase())) {
                milista.add(obj);
            }

        }
        AdapterUsuario adapter = new AdapterUsuario(milista);
        rv.setAdapter(adapter);
    }

}
