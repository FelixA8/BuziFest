package com.example.buzifest.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.buzifest.Data.UserPortfolio
import com.example.buzifest.Data.generateUUID
import com.example.buzifest.Helper.*
import com.example.buzifest.MainActivity
import com.example.buzifest.R

class BuyConfirmationActivity : AppCompatActivity() {

    private lateinit var storeName: TextView
    private lateinit var storeType: TextView
    private lateinit var storeAddress: TextView
    private lateinit var fundingTarget: TextView
    private lateinit var dividendPeriod: TextView
    private lateinit var purchaseAmount: TextView
    private lateinit var ownershipPercentage: TextView
    private lateinit var button:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_confirmation)


        val sqliteDb = DatabaseHelper(this)
        val portfolioID = intent.getStringExtra("portfolioID")
        val currentPortfolio = sqliteDb.selectSpecificPortfolio(portfolioID!!)
        val portfolioData = sqliteDb.selectPurchaseAmountOfPortfolio(portfolioID)
        val amount = intent.getIntExtra("amount", 0)

        storeName = findViewById(R.id.confimation_store_name)
        storeType = findViewById(R.id.confirmation_store_type)
        storeAddress = findViewById(R.id.confirmation_store_address)
        fundingTarget = findViewById(R.id.confirmation_funding_target)
        dividendPeriod = findViewById(R.id.confirmation_dividend_period)
        purchaseAmount = findViewById(R.id.confirmation_purchase_amount)
        ownershipPercentage = findViewById(R.id.confirmation_ownership_percentage)
        button = findViewById(R.id.confirmation_button)

        storeName.text = currentPortfolio.storeName
        storeType.text = currentPortfolio.storeType
        storeAddress.text = currentPortfolio.address
        fundingTarget.text = "Rp " + currentPortfolio.fundingTarget.toString()
        dividendPeriod.text = currentPortfolio.dividendPayoutPeriod.toString() + "Months"

        purchaseAmount.text = "Rp " + amount
        val ownershipPercent = (amount.toDouble() / currentPortfolio.fundingTarget.toDouble()) * 100
        ownershipPercentage.text = String.format("%.2f%%", ownershipPercent)

        val sharedpreferences =
            getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)

        // button
        button.setOnClickListener{
            val prevPortfolio = sqliteDb.checkUserPortfolioExist(portfolioID)
            println("portfold: ${prevPortfolio}")
            if(prevPortfolio.id == "") {
                val userPortfolio = UserPortfolio(generateUUID(), currentEmail, portfolioID, amount, 0)
                addUserPortfolio(userPortfolio, this)
                changeBalance(amount, currentEmail,"sub",sharedpreferences)
                val intent = Intent(this, Home::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                val userPortfolio = UserPortfolio(prevPortfolio.id, currentEmail, portfolioID, prevPortfolio.purchaseAmount+amount, 0)
                addExistingUserPortfolio(userPortfolio, this)
                changeBalance(amount, currentEmail,"sub",sharedpreferences)
                val intent = Intent(this, Home::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }

    }
}