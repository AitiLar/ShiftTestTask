package com.shifttest.shifttestapp.retrofit

import com.shifttest.shifttestapp.model.BinListData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{bin}")
    fun getDataBin(@Path("bin") bin: String) : Call<BinListData>



}