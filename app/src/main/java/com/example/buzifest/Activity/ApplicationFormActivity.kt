package com.example.buzifest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.buzifest.Model.MainViewModel
import com.example.buzifest.R
import com.example.buzifest.databinding.ActivityApplicationFormBinding

class ApplicationFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApplicationFormBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityApplicationFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.applicationSubmitButton.setOnClickListener {
            if(binding.applicationBusinessName.text.isEmpty()) {

            } else if(binding.applicationBusinessAddress.text.isEmpty()) {

            } else if(binding.applicationBusinessProvince.text.isEmpty()) {

            } else if(binding.applicationBusinessType.text.isEmpty()) {

            } else if(binding.applicationBusinessDescription.text.isEmpty()) {

            } else if(binding.applicationFundingTarget.text.isEmpty()) {

            } else if(binding.applicationDividendPayoutPeriod.text.isEmpty()) {

            } else if(binding.applicationPublicSharedStockRatio.text.isEmpty()) {

            } else if(binding.applicationMainShareholder.text.isEmpty()) {

            } else if(binding.applicationPublisher.text.isEmpty()) {

            } else if(binding.applicationFullName.text.isEmpty()) {

            } else if(binding.applicationPhoneNumber.text.isEmpty()) {

            } else {
                val storeName = binding.applicationBusinessName.text.toString()
                val address = binding.applicationBusinessAddress.text.toString()
                val province = binding.applicationBusinessProvince.text.toString()
                val storeType = binding.applicationBusinessType.text.toString()
                val description = binding.applicationBusinessDescription.text.toString()
                val fundingTarget = binding.applicationFundingTarget.text.toString()
                val dividendPayoutPeriod = binding.applicationDividendPayoutPeriod.text.toString()
                val publicSharedStockRatio = binding.applicationPublicSharedStockRatio.text.toString()
                val mainShareHolder = binding.applicationMainShareholder.text.toString()
                val publisher = binding.applicationPublisher.text.toString()
                val fullName = binding.applicationFullName.text.toString()
                val phoneNumber = binding.applicationPhoneNumber.text.toString()
                viewModel.postDataToSheet(storeName, address, province, storeType, description, fundingTarget, dividendPayoutPeriod, publicSharedStockRatio, mainShareHolder, publisher, fullName, phoneNumber)
            }
            viewModel.getStatus().observe(this, {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
        }

    }
}