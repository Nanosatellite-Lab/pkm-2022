package com.pkm.smarttoilet.database.FecesColor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feces_warna")
data class FecesColorData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "hijau")
    val hijau: String,

    @ColumnInfo(name = "coklat")
    val coklat: String,

    @ColumnInfo(name = "hitam")
    val hitam: String,

    @ColumnInfo(name = "date")
    val date: String
)