package com.example.hospital_roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


// Dao is a interface which as all query methods
@Dao
interface HospitalDAO {

    @Insert()
    suspend fun insert(hospital:HospitalEntity)

    @Query("SELECT * FROM hospital_table ORDER BY id ASC ")
    fun readAllData():LiveData<List<HospitalEntity>>
}