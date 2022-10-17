package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.project.databinding.ActivityOrganizacionBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var  binding: ActivityOrganizacionBinding
private lateinit var firebaseAuth: FirebaseAuth

class Activity_Organizacion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organizacion)

        val buttonBuscarUsuarios : Button = findViewById(R.id.alimentosbtton)
        buttonBuscarUsuarios.setOnClickListener {

            val intent : Intent = Intent(this, MainActivityUsuarios::class.java)
            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivityOrganizacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLog.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
        binding.editInven.setOnClickListener {
            startActivity(Intent(this, productosOrg::class.java))
        }
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
}