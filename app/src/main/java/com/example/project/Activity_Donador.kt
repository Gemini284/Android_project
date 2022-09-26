package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class Activity_Donador : AppCompatActivity() {
    private  lateinit var  binding: ActivityProfileBinding
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth

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

    fun mapas(view: View?){
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

}
