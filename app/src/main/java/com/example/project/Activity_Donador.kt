package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Activity_Donador : AppCompatActivity() {
    private  lateinit var  binding: ActivityProfileBinding
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.buttonLog.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        //codigo para mostrar email y nombre de usuario

        setText()

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

    private fun setText() {
        //codigo para mostrar email y nombre de usuario
        database = FirebaseDatabase.getInstance().getReference("Usuarios")
        var email = firebaseAuth.currentUser?.email
        binding.email.text = email

            database.child("User1").get().addOnSuccessListener {

                if (it.exists()){

                    val name = it.child("Nombre").value
                    binding.Nombre.text = "pepe"

                } else {
                    Toast.makeText(this, "El usuario no existe",Toast.LENGTH_SHORT).show()

                }
                }.addOnFailureListener {
                    Toast.makeText(this, "FALLO", Toast.LENGTH_SHORT).show()
            }
        }
    }


