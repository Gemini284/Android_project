package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CrearEvento_ORG : AppCompatActivity() {

    lateinit var etDirection: EditText
    lateinit var etDate: EditText
    lateinit var btnInsertData: Button

    lateinit var  dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_evento_org)

        etDirection = findViewById(R.id.eteventDirection)
        etDate = findViewById(R.id.eteventDate)
        btnInsertData = findViewById(R.id.btnInsertData)

        dbRef = FirebaseDatabase.getInstance().getReference("Eventos")

        btnInsertData.setOnClickListener {
            saveEventoData()
        }
    }

        private fun saveEventoData() {
            //Obteniendo valores
            val eventDirection = etDirection.text.toString()
            val eventDate = etDate.text.toString()

            //si no truena
            if(eventDirection.isEmpty()){
                etDirection.error = "Escriba una direccion"
            }
            if(eventDate.isEmpty()){
                etDate.error = "Escriba una fecha"
            }

            val eventId = dbRef.push().key!!

            val evento = EventModel(eventId,eventDirection,eventDate)

            dbRef.child(eventId).setValue(evento)
                .addOnCompleteListener{
                    Toast.makeText(this, "Evento creado",Toast.LENGTH_LONG).show()

                    etDirection.text.clear()
                    etDate.text.clear()

                }.addOnFailureListener{ err ->
                    Toast.makeText(this, "Error ${err.message}",Toast.LENGTH_LONG).show()
                }
        }
    }