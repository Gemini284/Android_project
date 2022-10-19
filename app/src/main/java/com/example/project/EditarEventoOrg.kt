package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.project.databinding.ActivityEditarEventoOrgBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditarEventoOrg : AppCompatActivity() {

    lateinit var binding : ActivityEditarEventoOrgBinding
    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarEventoOrgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener{
            val eventId = binding.nuevaID.text.toString()
            val nuevaDire = binding.nuevaDire.text.toString()
            val nuevaFecha = binding.nuevaFecha.text.toString()

            updateData(eventId,nuevaDire,nuevaFecha)
        }


    }

    private fun updateData(eventId: String, nuevaDire: String, nuevaFecha: String) {
        database = FirebaseDatabase.getInstance().getReference("Eventos")
        val event = mapOf<String,String>(
            "eventDate" to nuevaFecha,
            "eventDirection" to nuevaDire
        )

        database.child(eventId).updateChildren(event).addOnSuccessListener {
            binding.nuevaID.text.clear()
            binding.nuevaDire.text.clear()
            binding.nuevaFecha.text.clear()
            Toast.makeText(this,"Se ha editado correctamente",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
        }

    }
}