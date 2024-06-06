package com.example.buzifest.Activity

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.buzifest.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task

class PortfolioDetail : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var myMap: GoogleMap
    private var currLocation: Location = Location("").apply {
        longitude = 106.78113
        latitude = -6.20201
    }
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio_detail)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
    }
    fun getLastLocation(){
        //Get the permission of the device and set the lat and lng of the location.
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)

            return
        }
        //If permission granted, then set the location.
        var task: Task<Location> = fusedLocationProviderClient.getLastLocation()
        task.addOnSuccessListener { location: Location? ->
            if(location != null) {
                currLocation = location
            } 
            var mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.detail_maps) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1) {
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            } else {
                Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Render the google maps
    override fun onMapReady(p0: GoogleMap) {
        myMap = p0
        var myLocation = LatLng(currLocation.latitude, currLocation.longitude)
        var cameraPosition = CameraPosition.builder().target(myLocation).zoom(20.0F).build()
        myMap.addMarker(MarkerOptions().position(myLocation).title("Location"))
        myMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        myMap.isBuildingsEnabled = true
    }
}