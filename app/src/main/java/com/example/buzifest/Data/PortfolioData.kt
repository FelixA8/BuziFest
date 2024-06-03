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
    "soto madura 99",
    "Jl. Juanda",
    "Jambi",
    "https://blog.meyerfood.id/wp-content/uploads/2020/09/soto-ayam-madura.jpg",
    "Food n Beverages",
    3000000,
    "lorem ipsum fdas",
    20.0,
    3,
    "FelixA",
    "Soto Dunia ID",
    100000,
))