package com.example.buzifest.Data

data class UserPortfolio(
    val userEmail:String,
    var portfolioID:String,
    val purchaseAmount:Int,
    val totalProfit:Int
)