package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PuntoRecoleccionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_punto_recoleccion)
        if(savedInstanceState == null){
            val fragment = MapsFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.map_placeholder, fragment)
                .commitAllowingStateLoss()
        }
    }
}
