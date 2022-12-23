package com.shifttest.shifttestapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    var apiService: ApiService? = null
    fun getInstance() : ApiService {
        if (apiService == null) {
            apiService = Retrofit.Builder()
                .baseUrl("https://lookup.binlist.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
        return apiService!!
    }
}