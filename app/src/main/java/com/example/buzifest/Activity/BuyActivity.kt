package com.example.buzifest.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.buzifest.Helper.*
import com.example.buzifest.MainActivity
import com.example.buzifest.R

class BuyActivity : AppCompatActivity() {

    private lateinit var amount: EditText
    private lateinit var continueButton: TextView
    private lateinit var tvOutOf: TextView
    private lateinit var buyBackButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)
        val sqliteDb = DatabaseHelper(this)

        tvOutOf = findViewById(R.id.buy_tvOutOf)
        amount = findViewById(R.id.buy_etAmount)
        continueButton = findViewById(R.id.buy_continue)
        buyBackButton = findViewById(R.id.buy_back_button)

        val portfolioID = intent.getStringExtra("portfolioID")
        val totalInvested = sqliteDb.selectPurchaseAmountOfPortfolio(portfolioID!!).totalInvested
        val portfolio = sqliteDb.selectSpecificPortfolio(portfolioID)

        tvOutOf.text = "Rp. ${formatNumber(portfolio.fundingTarget - totalInvested)} left"
        val sharedpreferences =
            getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        currentBalance = sharedpreferences.getString(MainActivity.BALANCE_KEY, null).toString().toInt()
        continueButton.setOnClickListener {
            if((portfolio.fundingTarget - totalInvested) < amount.text.toString().toInt()) {
                Toast.makeText(this, "Too Many Funds", Toast.LENGTH_SHORT).show()
            } else if (amount.text.toString().toInt() > currentBalance){
                Toast.makeText(this, "Balance Not Enough!", Toast.LENGTH_SHORT).show()
            } else {
                val amountString = amount.text.toString()
                val amountInt = amountString.toIntOrNull() ?: 0

                val intent = Intent(this, BuyConfirmationActivity::class.java).apply {
                    putExtra("amount", amountInt)
                    putExtra("portfolioID", portfolioID)
                }
                startActivity(intent)
            }
        }
        buyBackButton.setOnClickListener {
            finish()
        }
    }
}