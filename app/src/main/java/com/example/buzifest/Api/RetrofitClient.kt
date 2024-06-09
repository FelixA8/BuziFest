package com.example.buzifest.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://script.google.com/macros/s/AKfycbw_gGy05LPILtYYFsQvPy5sxx4Kqo5onwWa-fuAWjLiPLcFA8apm3xyHySMYXkEIB3x/"

    val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    val instance = retrofit.create(Api::class.java)
}