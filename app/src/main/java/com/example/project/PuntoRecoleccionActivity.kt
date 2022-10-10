package com.example.project

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.MapFragment
import com.google.android.material.internal.ContextUtils


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
