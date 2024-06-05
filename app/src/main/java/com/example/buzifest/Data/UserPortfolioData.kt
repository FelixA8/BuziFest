package com.example.buzifest.Data

import java.util.UUID

data class UserPortfolio(
    val id:String,
    val userEmail:String,
    var portfolioID:String,
    val purchaseAmount:Int,
    val totalProfit:Int
)


val DUMMY_USERPORTFOLIODATA = arrayOf(
    UserPortfolio(
        generateUUID(),
        "felix.anderson@gmail.com",
        DUMMY_PORTFOLIODATA[0].id,
        20000,
        0,
    )
)