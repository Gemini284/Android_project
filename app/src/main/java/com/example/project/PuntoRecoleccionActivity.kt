package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.SupportMapFragment


class PuntoRecoleccionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_punto_recoleccion)

        //Añadir fragmento
        if (savedInstanceState == null) {
            val fragment = MapsFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.map_placeholder, fragment)
                .commitAllowingStateLoss()
        }
    }


}
