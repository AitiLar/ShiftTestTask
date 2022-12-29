package com.shifttest.shifttestapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BinListData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao() : DataBaseDao
    companion object {
        fun getDataseClient(context: Context) : AppDatabase {
            return Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java,
                    "datahis.db")
                    .fallbackToDestructiveMigration()
                    .build()

        }

    }
}