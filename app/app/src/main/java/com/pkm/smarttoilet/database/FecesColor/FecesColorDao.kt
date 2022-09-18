package com.pkm.smarttoilet.database.FecesColor

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface FecesColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fecesColor: FecesColorData)

    @Delete
    fun delete(fecesColor: FecesColorData)

    @Query("SELECT * FROM feces_warna LIMIT -1 OFFSET 14")
    fun getFecesColorData(): LiveData<List<FecesColorData>>


}