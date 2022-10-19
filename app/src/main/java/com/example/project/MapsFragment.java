package com.example.project;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsFragment extends Fragment {

    FusedLocationProviderClient client;
    LatLng current;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            //googleMap.addMarker(new MarkerOptions().position(current).title("YOU"));
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(current));
            String string = String.format("%s", current);
            Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        // Initialize location client
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        new View.OnClickListener() {
            @Override public void onClick(View view){
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else { //permiso no dado
                    Toast.makeText(getActivity(), "NO FUNCIONA1", Toast.LENGTH_SHORT).show();
                    requestPermissions(
                            new String[] {
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                            }, 100
                    );
                }
            }
        };


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && (grantResults.length > 0)
                && (grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
            Toast.makeText(getActivity(), "SI FUNCIONA2", Toast.LENGTH_SHORT).show();
            getCurrentLocation();
        } else { //permiso no dado
            Toast.makeText(getActivity(), "NO FUNCIONA2", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            Toast.makeText(getActivity(), "SI FUNCIONA3", Toast.LENGTH_SHORT).show();
            client.getLastLocation().addOnCompleteListener(task -> {
                Location location = task.getResult();
                if (location != null) {
                    current = new LatLng(location.getLatitude(), location.getLongitude());
                    String string = String.format("%s", current);
                    Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();

                } else { //location request
                    Toast.makeText(getActivity(), "NO FUNCIONA4", Toast.LENGTH_SHORT).show();
                    LocationRequest locationRequest = new LocationRequest()
                            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                            .setInterval(10000)
                            .setFastestInterval(1000)
                            .setNumUpdates(1);

                    LocationCallback locationCallback = new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            Location location1 = locationResult.getLastLocation();
                            current = new LatLng(location1.getLatitude(), location1.getLongitude());
                            String string = String.format("%s", current);
                            Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
                        }
                    };

                    //actualizacion de ubicacion
                    client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                }
            });
        } else { //si no esta la ubicacion habilitada
            Toast.makeText(getActivity(), "NO FUNCIONA3", Toast.LENGTH_SHORT).show();
            startActivity(
                    new Intent(
                            Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}