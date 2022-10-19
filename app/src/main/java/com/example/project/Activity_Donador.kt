package com.example.project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase

class Activity_Donador : AppCompatActivity() {
    private lateinit var  binding: ActivityProfileBinding
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: User
    private var nombre = "pepe"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Profile"
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        /*al uid = firebaseAuth.currentUser?.uid
        var nombre = "pepe"
        databaseReference = FirebaseDatabase.getInstance().getReference(uid!!)
        databaseReference.child("Nombre").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
                //binding.Nombre.setText(user.) comento esto porque no dejaba probar xd
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })*/

        binding.buttonLog.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
        binding.alimentosbttn.setOnClickListener {
            startActivity(Intent(this, Productos::class.java))
        }
        binding.bttnEvento.setOnClickListener {
            startActivity(Intent(this,Evento_Usuario::class.java))
        }
        binding.bttnPuntos.setOnClickListener{
            startActivity(Intent(this, PuntoRecoleccionActivity::class.java))
        }
        binding.CrearORG.setOnClickListener {
            startActivity(Intent(this, Donacion::class.java))
        }
        binding.email.text = firebaseAuth.currentUser?.email.toString()
        muestraNombre()
        //binding.Nombre.text = nombre

    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            val email = firebaseUser.email
        }
        else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun muestraNombre(){

        databaseReference = Firebase.database.getReference("Usuarios")
        val firebaseUser = firebaseAuth.currentUser
        val uid = firebaseUser?.uid
        if (uid != null) {
            databaseReference.child(uid).child("Nombre").get().addOnSuccessListener {
                binding.Nombre.text = it.value.toString()
            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }
        }
    }
}
