package com.example.buzifest.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.buzifest.R

class BuyActivity : AppCompatActivity() {

    private lateinit var amount: EditText
    private lateinit var continueButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        amount = findViewById(R.id.buy_etAmount)
        continueButton = findViewById(R.id.buy_continue)

        val portfolioID = intent.getStringExtra("portfolioID")

        continueButton.setOnClickListener {
            val amountString = amount.text.toString()
            val amountInt = amountString.toIntOrNull() ?: 0
            val intent = Intent(this, BuyConfirmationActivity::class.java).apply {
                putExtra("amount", amountInt)
                putExtra("portfolioID", portfolioID)
            }
            startActivity(intent)
        }

    }
}