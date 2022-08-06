package com.pkm.smarttoilet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FecesResultViewModel: ViewModel() {
    private val _myData = MutableLiveData<String>()
    val myData: LiveData<String> = _myData

    fun addText(updatedNum: String){
        _myData.value = updatedNum
        Log.d(TAG, "addText: ${myData.value} ${_myData.value}")
    }
    companion object {
        private const val TAG = "FecesResultViewModel"
    }
}