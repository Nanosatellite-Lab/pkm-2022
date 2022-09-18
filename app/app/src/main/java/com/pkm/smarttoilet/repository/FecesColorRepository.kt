package com.pkm.smarttoilet.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.pkm.smarttoilet.database.FecesColor.FecesColorDao
import com.pkm.smarttoilet.database.FecesColor.FecesColorData
import com.pkm.smarttoilet.database.FecesColor.FecesColorDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FecesColorRepository(application: Application) {
    private val mFecesColorDao: FecesColorDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FecesColorDatabase.getDatabase(application)
        mFecesColorDao = db.fecesColorDao()
    }

    fun getFecesData(): LiveData<List<FecesColorData>> = mFecesColorDao.getFecesColorData()

    fun insert(fecesColor: FecesColorData){
        executorService.execute { mFecesColorDao.insert(fecesColor) }
    }
    fun delete(fecesColor: FecesColorData){
        executorService.execute { mFecesColorDao.delete(fecesColor) }
    }
}