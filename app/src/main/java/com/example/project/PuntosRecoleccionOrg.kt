package com.example.project

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PuntosRecoleccionOrg : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntos_recoleccion_org)

        val actionBar = supportActionBar!!
        actionBar.title = "Puntos de Recolección"

        //Añadir fragmento
        if (savedInstanceState == null) {
            val fragment = MapsFragmentOrg()
            supportFragmentManager.beginTransaction()
                .add(R.id.map_placeholder2, fragment)
                .commitAllowingStateLoss()
        }
    }
}