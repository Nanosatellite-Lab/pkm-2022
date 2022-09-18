package com.pkm.smarttoilet.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pkm.smarttoilet.database.FecesColor.FecesColorData
import com.pkm.smarttoilet.repository.FecesColorRepository

class FecesHistoryViewModel(application: Application): ViewModel() {
    private val mFecesColorRepository: FecesColorRepository = FecesColorRepository(application)
    fun insert(fecesColorData: FecesColorData){
        mFecesColorRepository.insert(fecesColorData)
    }
    fun delete(fecesColorData: FecesColorData){
        mFecesColorRepository.delete(fecesColorData)
    }
    fun getFecesColorData(): LiveData<List<FecesColorData>> = mFecesColorRepository.getFecesData()
}
