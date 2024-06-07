package com.example.buzifest.Activity

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.buzifest.Helper.DatabaseHelper
import com.example.buzifest.Helper.formatNumber
import com.example.buzifest.R
import com.example.buzifest.databinding.ActivityPortfolioDetailBinding
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PortfolioDetail : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var myMap: GoogleMap
    private var currLocation: Location = Location("").apply {
        longitude = 106.78113
        latitude = -6.20201
    }
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPortfolioDetailBinding.inflate(layoutInflater)
        val sqliteDb = DatabaseHelper(this)

        setContentView(binding.root)

        val portfolioID = intent.getStringExtra("portfolioID")
        val currentPortfolio = sqliteDb.selectSpecificPortfolio(portfolioID!!)
        val portfolioData = sqliteDb.selectPurchaseAmountOfPortfolio(portfolioID)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()

        val amount = portfolioData.totalInvested // Amount of invested portfolio
        val totalInvestor = portfolioData.totalInvestor // Amount of total investors in a portfolio
        //Setting the Data
        binding.detailAddress.text = currentPortfolio.address
        binding.profileImageView.setOnClickListener {
            finish()
        }
        Glide.with(this)
            .load(currentPortfolio.image)
            .into(binding.detailStoreImage)
        binding.detailStoreName.text = currentPortfolio.storeName
        binding.detailStoreType.text = currentPortfolio.storeType
        binding.detailTotalInvested.text = formatNumber(amount)
        binding.detailFundingTarget.text = formatNumber(currentPortfolio.fundingTarget)
        lifecycleScope.launch {
            if (currentPortfolio.fundingTarget != 0) {
                withContext(Dispatchers.Main){
                    binding.detailCustomProgressBar.progress = ((amount.toDouble()/currentPortfolio.fundingTarget.toDouble())*100).toInt()
                }
            } else {
                withContext(Dispatchers.Main){
                    binding.detailCustomProgressBar.progress = 0
                }
            }
        }
        binding.detailTotalInvestor.text = totalInvestor.toString()
        binding.detailDescription.text = currentPortfolio.description
        binding.detailOwnershipPublicSharedStock.text = "${currentPortfolio.publicShareStock.toString()}%"
        binding.detailFundingTarget.text = "Rp ${formatNumber(currentPortfolio.fundingTarget)}"
        binding.detailOwnershipDividenPayoutPeriod.text = "${currentPortfolio.dividendPayoutPeriod} months"
        binding.detailOwnershipMainShareHolder.text = currentPortfolio.mainShareHolder
        binding.detailOwnershipPublisher.text = currentPortfolio.publisher
        binding.detailLocationDetailProvince.text = currentPortfolio.province
        binding.detailLocationDetailAddress.text = currentPortfolio.address

        //Set BuyButton
        binding.detailBuyButton.setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java).apply {
                putExtra("portfolioID", portfolioID)
            }
            startActivity(intent)
        }
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