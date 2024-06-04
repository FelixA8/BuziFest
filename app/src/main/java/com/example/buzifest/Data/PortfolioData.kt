package com.example.buzifest.Data

import java.util.*

data class Portfolio(
    val id:String,
    val storeName:String,
    val address:String,
    val province:String,
    val image:String,
    val logo:String,
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