package com.example.buzifest.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buzifest.Helper.DatabaseHelper
import com.example.buzifest.Helper.currentBalance
import com.example.buzifest.Helper.currentEmail
import com.example.buzifest.Helper.formatNumber
import com.example.buzifest.databinding.ActivityWithdrawBinding

class WithdrawActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWithdrawBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWithdrawBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sqliteDb = DatabaseHelper(this)
        val earnings = sqliteDb.selectUserSummaryValue(currentEmail).totalEarning

        binding.withdrawBalance.text = "Rp ${formatNumber(currentBalance)}"
        binding.withdrawEarnings.text = "Rp ${formatNumber(earnings)}"

        binding.withdrawButtonBalance.setOnClickListener {
            val intent = Intent(this, ConfirmWithdrawActivity::class.java).apply {
                putExtra("status", "withdraw_balance")
            }
            startActivity(intent)
        }
        binding.withdrawButtonEarnings.setOnClickListener {
            val intent = Intent(this, ConfirmWithdrawActivity::class.java).apply {
                putExtra("status", "withdraw_earnings")
            }
            startActivity(intent)
        }
    }
}