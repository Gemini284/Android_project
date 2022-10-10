package com.example.project

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.SupportMapFragment

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
