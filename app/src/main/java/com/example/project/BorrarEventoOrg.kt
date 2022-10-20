package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.ActivityBorrarEventoOrgBinding
import com.google.firebase.database.*

class BorrarEventoOrg : AppCompatActivity() {

    lateinit var binding: ActivityBorrarEventoOrgBinding
    lateinit var dbRef: DatabaseReference
    lateinit var eventRecyclerView: RecyclerView
    lateinit var eventArrayList: ArrayList<Event>
    lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrarEventoOrgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.borrar.setOnClickListener {

            var eventID = binding.Fecha.text.toString()
            if (eventID.isEmpty())
                Toast.makeText(this, "Escriba el id del evento", Toast.LENGTH_SHORT).show()
            else {
                deleteData(eventID)
            }


        }
    }


        private fun deleteData(eventID: String) {
            dbRef = FirebaseDatabase.getInstance().getReference("Eventos")
            dbRef.child(eventID).removeValue().addOnSuccessListener {
                Toast.makeText(this, "El evento se ha borrado", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }


    }

