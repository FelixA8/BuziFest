package com.example.buzifest.Api

import retrofit2.Call
import com.example.buzifest.Data.PostResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("exec")
    fun postDataToSheet(
        @Field("storeName") storeName : String,
        @Field("address") address : String,
        @Field("province") province : String,
        @Field("storeType") storeType : String,
        @Field("description") description : String,
        @Field("fundingTarget") fundingTarget : String,
        @Field("dividendPayoutPeriod") dividendPayoutPeriod : String,
        @Field("publicSharedStockRatio") publicSharedStockRatio : String,
        @Field("mainShareHolder") mainShareHolder : String,
        @Field("publisher") publisher : String,
        @Field("fullName") fullName : String,
        @Field("phoneNumber") phoneNumber : String,
    ): Call<PostResponse>
}