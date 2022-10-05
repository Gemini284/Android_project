package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Evento_ORG : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evento_org)
    }

    //Cambiar actividad a crearEventoORG
    fun crearEvento(v: View?){
        val intent = Intent(this,CrearEvento_ORG::class.java)
        startActivity(intent)
    }
}