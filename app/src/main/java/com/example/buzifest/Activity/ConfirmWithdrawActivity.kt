package com.example.buzifest.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.buzifest.Helper.*
import com.example.buzifest.MainActivity
import com.example.buzifest.R
import com.example.buzifest.databinding.ActivityConfirmWithdrawBinding

class ConfirmWithdrawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityConfirmWithdrawBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sqliteDb = DatabaseHelper(this)

        val sharedpreferences =
            getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)

        val earnings = sqliteDb.selectUserSummaryValue(currentEmail).totalEarning
        val status = intent.getStringExtra("status").toString()

        binding.withdrawConfirmationBackButton.setOnClickListener {
            finish()
        }
        if(status == "withdraw_balance") {
            binding.withdrawConfirmationTvOutOf.text = "Out of Rp ${currentBalance}"
            binding.withdrawConfirmationContinue.setOnClickListener {
                if(binding.withdrawConfirmationEtAmount.text.isEmpty() || binding.withdrawConfirmationEtAmount.text.toString().toInt() < 0 || binding.withdrawConfirmationEtAmount.text.toString().toInt() > currentBalance) {
                    Toast.makeText(this, "Please use a correct input the field", Toast.LENGTH_SHORT).show()
                } else {
                    println("success!")
                    changeBalance(binding.withdrawConfirmationEtAmount.text.toString().toInt(), currentEmail, "sub", sharedpreferences)
                    val intent = Intent(this, Home::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        } else if(status == "withdraw_earnings") {
            binding.withdrawConfirmationTitle.text = "Withdraw Earnings"
            binding.withdrawConfirmationEtAmount.isEnabled = false
            binding.withdrawConfirmationEtAmount.setText(earnings.toString())
            binding.withdrawConfirmationContinue.setOnClickListener {
                withdrawUserEarnings(currentEmail)
                sqliteDb.clearUserEarnings(currentEmail)
                val intent = Intent(this, Home::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}