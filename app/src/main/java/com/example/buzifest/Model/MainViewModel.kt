package com.example.buzifest.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buzifest.Api.RetrofitClient
import com.example.buzifest.Data.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class MainViewModel:ViewModel() {
    val status = MutableLiveData<String>()

    fun postDataToSheet(storeName:String, address:String, province:String, storeType:String, description:String, fundingTarget:String, dividendPayoutPeriod:String,
                        publicSharedStockRatio:String, mainShareHolder:String,publisher:String,fullName:String,phoneNumber:String) {
        RetrofitClient.instance.postDataToSheet(storeName, address, province, storeType, description, fundingTarget, dividendPayoutPeriod, publicSharedStockRatio, mainShareHolder, publisher, fullName, phoneNumber)
            .enqueue(object : Callback<PostResponse>{
                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    if(response.isSuccessful) {
                        status.postValue(response.body()?.status)
                    }
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    t.message?.let { Log.e("onFailure", it) }
                }
            })
    }

    fun getStatus():LiveData<String> {
        return status
    }
}