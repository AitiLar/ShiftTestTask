package com.shifttest.shifttestapp.repository

import android.content.Context
import com.shifttest.shifttestapp.model.AppDatabase
import com.shifttest.shifttestapp.model.BinListData
import com.shifttest.shifttestapp.model.DataBaseDao
import com.shifttest.shifttestapp.retrofit.ApiService
import com.shifttest.shifttestapp.retrofit.ApiService.Companion.retrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class Repository(private val dao: DataBaseDao, private val context: Context) {

  val allPost: Flow<List<BinListData>> = dao.findAll()
  fun getAllData(bin: String) = retrofitService?.getDataBin(bin)


  var appDatabase: AppDatabase? = null

  fun initializeDB(context: Context) : AppDatabase {
    return AppDatabase.getDataseClient(context)
  }

  fun insertData(data: BinListData) {
    appDatabase = initializeDB(context)

    CoroutineScope(Dispatchers.IO).launch {
      appDatabase!!.appDao().insert(data)
    }

  }




}

