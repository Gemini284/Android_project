package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import com.example.project.databinding.ActivityOrganizacionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private lateinit var  binding: ActivityOrganizacionBinding
private lateinit var firebaseAuth: FirebaseAuth
private lateinit var databaseReference: DatabaseReference


class Activity_Organizacion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organizacion)

        val actionBar = supportActionBar!!
        actionBar.title = "Perfil de Organización"

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
        binding.bttnPuntos.setOnClickListener{
            startActivity(Intent(this, PuntosRecoleccionOrg::class.java))
        }
        binding.bttnEvento.setOnClickListener {
            startActivity(Intent(this, Evento_ORG::class.java))
        }
        binding.Dona.setOnClickListener {
            startActivity(Intent(this, UserListActivity::class.java))
        }
        binding.textViewEmpresa.text = firebaseAuth.currentUser?.email.toString()
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
    }//xdxdxdxd

    fun irEventoORG(v: View?) {
        val intent = Intent(this, Evento_ORG::class.java)
        startActivity(intent)
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