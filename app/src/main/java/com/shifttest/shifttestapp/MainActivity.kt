package com.shifttest.shifttestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.shifttest.shifttestapp.model.BinListData
import com.shifttest.shifttestapp.retrofit.ApiClient
import com.shifttest.shifttestapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var txt: TextView
    lateinit var mService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById(R.id.txt)
        mService = ApiClient.getInstance()
        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getDataBin("45717360").enqueue(object : Callback<BinListData> {
            override fun onFailure(call: Call<BinListData>, t: Throwable) {
                txt.text = t.message
            }

            override fun onResponse(call: Call<BinListData>, response: Response<BinListData>) {
                var t = response.body()?.brand
                txt.text = t
            }


        })
    }
}