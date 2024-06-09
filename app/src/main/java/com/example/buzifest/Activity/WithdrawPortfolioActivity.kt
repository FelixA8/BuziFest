package com.example.buzifest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buzifest.databinding.ActivityWithdrawPortfolioBinding

class WithdrawPortfolioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityWithdrawPortfolioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}