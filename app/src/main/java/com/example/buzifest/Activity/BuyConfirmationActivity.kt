package com.example.buzifest.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.buzifest.Data.UserPortfolio
import com.example.buzifest.Data.generateUUID
import com.example.buzifest.Helper.DatabaseHelper
import com.example.buzifest.Helper.addUserPortfolio
import com.example.buzifest.Helper.currentEmail
import com.example.buzifest.R
import kotlinx.coroutines.launch

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

        // button
        button.setOnClickListener{
            lifecycleScope.launch {
                try {
                    val userPortfolio = UserPortfolio(generateUUID(), currentEmail, portfolioID, amount, 0)
                    addUserPortfolio(userPortfolio, this@BuyConfirmationActivity)
                    val intent = Intent(this@BuyConfirmationActivity, Home::class.java)
                    Toast.makeText(this@BuyConfirmationActivity, "Success!", Toast.LENGTH_SHORT).show()
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } catch (e:Exception) {
                    println(e)
                }
            }
        }

    }
}