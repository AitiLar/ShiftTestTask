package com.shifttest.shifttestapp.repository

import android.app.Application
import com.shifttest.shifttestapp.model.AppDatabase

class GlobalApplication: Application() {
    val database by lazy { AppDatabase.getDataseClient(this) }
    val repository by lazy { Repository(database.appDao(), this) }
}