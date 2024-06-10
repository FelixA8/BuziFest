package com.example.buzifest.Activity

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.example.buzifest.Helper.DatabaseHelper
import com.example.buzifest.Helper.formatNumber
import com.example.buzifest.R
import com.example.buzifest.databinding.ActivityFulfilledPortfolioDetailBinding
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

class FulfilledPortfolioDetail : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityFulfilledPortfolioDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Rest of your initialization code
        val sqliteDb = DatabaseHelper(this)
        val portfolioID = intent.getStringExtra("portfolioID")
        val userPortfolioID = intent.getStringExtra("userPortfolioID")
        val userPortfolio = sqliteDb.selectSpecificUserPortfolio(userPortfolioID!!)
        val currentPortfolio = sqliteDb.selectSpecificPortfolio(portfolioID!!)
        val portfolioData = sqliteDb.selectPurchaseAmountOfPortfolio(portfolioID)
        val ownershipPercent = (userPortfolio.purchaseAmount.toDouble() / currentPortfolio.fundingTarget.toDouble()) * 100

        Glide.with(this)
            .load(currentPortfolio.image)
            .into(binding.fulfilledStoreImage)

        binding.fulfilledStoreName.text = currentPortfolio.storeName
        binding.fulfilledAddress.text = currentPortfolio.address
        binding.fulfilledTotalInvestor.text = portfolioData.totalInvestor.toString()
        binding.fulfilledPurchaseAmount.text = "Rp ${formatNumber(userPortfolio.purchaseAmount)}"
        binding.fulfilledOwnershipPercentage.text = String.format("%.2f%%", ownershipPercent)
        binding.fulfilledEarnings.text = "Rp ${formatNumber(userPortfolio.earnings)}"
        binding.fulfilledDescription.text = currentPortfolio.description
        binding.fulfilledOwnershipPublicSharedStock.text = "${currentPortfolio.publicShareStock}%"
        binding.fulfilledOwnershipFundingTarget.text = "Rp ${formatNumber(currentPortfolio.fundingTarget)}"
        binding.fulfilledOwnershipDividenPayoutPeriod.text = "${currentPortfolio.dividendPayoutPeriod} months"
        binding.fulfilledOwnershipMainShareHolder.text = currentPortfolio.mainShareHolder
        binding.fulfilledOwnershipPublisher.text = currentPortfolio.publisher
        binding.fulfilledLocationDetailProvince.text = currentPortfolio.province
        binding.fulfilledLocationDetailAddress.text = currentPortfolio.address

        binding.fulfilledImageView.setOnClickListener {
            finish()
        }
    }
}
