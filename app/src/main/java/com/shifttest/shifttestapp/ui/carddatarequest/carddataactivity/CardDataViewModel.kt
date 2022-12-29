package com.shifttest.shifttestapp.ui.carddatarequest.carddataactivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shifttest.shifttestapp.repository.Repository
import com.shifttest.shifttestapp.model.BinListData
import com.shifttest.shifttestapp.model.DataBaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardDataViewModel(private val repository: Repository): ViewModel() {
    val dataList = MutableLiveData<BinListData>()
    val errorMessage = MutableLiveData<String>()
    val data: LiveData<List<BinListData>> = repository.allPost.asLiveData()

    fun getAllData(bin: String) {
        val response = repository.getAllData(bin)
        response?.enqueue(object : Callback<BinListData> {
            override fun onResponse(call: Call<BinListData>, response: Response<BinListData>) {
                dataList.postValue(response.body())
                response.body()?.let {
                    it.bin = bin
                    postDataBase(it)
                }
            }

            override fun onFailure(call: Call<BinListData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

    }

    fun postDataBase(bin: BinListData) {
        repository.insertData(bin)

    }


}


