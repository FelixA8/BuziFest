package com.example.buzifest.Data

import android.os.Parcelable
import java.util.*

data class Portfolio(
    val id:String,
    val storeName:String,
    val address:String,
    val province:String,
    val image:String,
    val storeType:String,
    val logo:String,
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
    "Ayam Sunib",
    "Jl. Sudirman",
    "Pekanbaru",
    "https://mediacdn.quipper.com/media/W1siZiIsIjIwMTgvMTAvMDQvMDgvMjcvNTQvOWNiZDM0MDQtODM0MC00MDBiLThjMmYtNTc4MWQ0M2NjYmVjLyJdLFsicCIsInRodW1iIiwiMTIwMHhcdTAwM2UiLHt9XSxbInAiLCJjb252ZXJ0IiwiLWNvbG9yc3BhY2Ugc1JHQiAtc3RyaXAiLHsiZm9ybWF0IjoianBnIn1dXQ.jpg?sha=3ede08f46ecd185f",
    "Tech Store",
    "https://lh3.googleusercontent.com/uhkzbcunvxzgdvHRZr7StTuO_J_sfUVJYbu4k_PJIZW9pRAkEmj0Cc8ESpAnkcwSQB-gY6txUbs6OgqOAK1SzHof89ckongvczh-=w480",
    2230000,
    "lorem ipsum dsfadas",
    10.0,
    2,
    "Nellie",
    "Sunib Co.",
    1200000,
))