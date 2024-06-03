package com.example.buzifest.Data

import java.util.*

data class Portfolio(
    val id:String,
    val storeName:String,
    val address:String,
    val province:String,
    val image:String,
    val storeType:String,
//    val fundingCollected:Int,
    val fundingTarget:Int,
//    val totalInvestor:Int,
    val description:String,
    val publicShareStock:Double,
    val dividendPayoutPeriod:Int,
    val mainShareHolder:String,
    val publisher:String,
    val grossProfit:Int,
)

fun generateUUID(): String {
    return UUID.randomUUID().toString()
}

public val DUMMY_PORTFOLIODATA = arrayOf(Portfolio(
    generateUUID(),
    "apple",
    "Jl. Sudirman",
    "Jakarta",
    "https://w7.pngwing.com/pngs/589/546/png-transparent-apple-logo-new-york-city-brand-computer-apple-company-computer-logo.png",
    "Food n Beverages",
    300000,
    "lorem ipsum",
    60.0,
    6,
    "Fi Tech",
    "Apple Inc.",
    1000000
),Portfolio(
    generateUUID(),
    "apple",
    "Jl. Sudirman",
    "Jakarta",
    "https://w7.pngwing.com/pngs/589/546/png-transparent-apple-logo-new-york-city-brand-computer-apple-company-computer-logo.png",
    "Food n Beverages",
    300000,
    "lorem ipsum",
    60.0,
    6,
    "Fi Tech",
    "Apple Inc.",
    1000000
),Portfolio(
    generateUUID(),
    "apple",
    "Jl. Sudirman",
    "Jakarta",
    "https://w7.pngwing.com/pngs/589/546/png-transparent-apple-logo-new-york-city-brand-computer-apple-company-computer-logo.png",
    "Food n Beverages",
    300000,
    "lorem ipsum",
    60.0,
    6,
    "Fi Tech",
    "Apple Inc.",
    1000000
))