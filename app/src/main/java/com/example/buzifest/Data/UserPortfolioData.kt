package com.example.buzifest.Data

data class UserPortfolio(
    val userEmail:String,
    var portfolioID:String,
    val purchaseAmount:Int,
    val totalProfit:Int
)

val DUMMY_USERPORTFOLIODATA = arrayOf(
    UserPortfolio(
        "felix.anderson@gmail.com",
        DUMMY_PORTFOLIODATA[0].id,
        20000,
        0,
    )
)
