package com.shifttest.shifttestapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface DataBaseDao {
    @Query("SELECT * FROM history")
    fun findAll(): Flow<List<BinListData>>

    @Insert
    fun insert(history: BinListData)
}