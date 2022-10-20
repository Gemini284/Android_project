package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.project.databinding.ActivityOrganizacionBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var  binding: ActivityOrganizacionBinding
private lateinit var firebaseAuth: FirebaseAuth

class Activity_Organizacion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organizacion)

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
        binding.CrearORG.setOnClickListener {
            startActivity(Intent(this, EmpresasAliadas::class.java))
        }
        binding.bttnPuntos.setOnClickListener{
            startActivity(Intent(this, PuntoRecoleccionActivity::class.java))
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

    fun irEventoORG(v: View?) {
        val intent = Intent(this, Evento_ORG::class.java)
        startActivity(intent)
    }
}