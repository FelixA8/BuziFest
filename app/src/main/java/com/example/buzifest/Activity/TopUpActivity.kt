package com.example.buzifest.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.buzifest.Helper.changeBalance
import com.example.buzifest.Helper.currentEmail
import com.example.buzifest.MainActivity
import com.example.buzifest.R
import com.example.buzifest.databinding.ActivityTopUpBinding

class TopUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityTopUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sharedpreferences =
            getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)

        binding.topupTvOutOf.text = "To: ${currentEmail}"
        binding.topupContinue.setOnClickListener {
            val topUpAmount = binding.topupEtAmount.text.toString().toInt()
            if(topUpAmount > 0) {
                changeBalance(topUpAmount, currentEmail, "add", sharedpreferences)
                finish()
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please Input a valid Value!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buyBackButton.setOnClickListener {
            finish()
        }
    }
}