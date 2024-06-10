package com.example.buzifest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buzifest.databinding.ActivityWithdrawBinding

class WithdrawActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWithdrawBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWithdrawBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}