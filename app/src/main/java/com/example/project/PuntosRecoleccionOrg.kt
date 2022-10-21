package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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