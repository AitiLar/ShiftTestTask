package com.shifttest.shifttestapp.ui.carddatarequest.carddataactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shifttest.shifttestapp.repository.Repository

class CardDataViewModelFactory constructor(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CardDataViewModel::class.java)) {
            CardDataViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}