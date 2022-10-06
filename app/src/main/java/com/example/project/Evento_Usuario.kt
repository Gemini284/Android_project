package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Evento_Usuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evento_usuario)
    }

    fun buscarEvento(v: View?) {
        val intent = Intent(this, BuscarEventoDonador::class.java)
        startActivity(intent)
    }
}
