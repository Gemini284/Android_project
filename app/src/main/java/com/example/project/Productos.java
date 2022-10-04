package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Productos extends AppCompatActivity {
    RecyclerView recyclerView;
    mainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos2);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Inventario"), MainModel.class)
                        .build();
        mainAdapter = new mainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

    }

        @Override
        protected void onStart () {
            super.onStart();
            mainAdapter.startListening();
        }

        @Override
        protected void onStop () {
            super.onStop();
            mainAdapter.stopListening();

        }
    }
