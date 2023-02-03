package com.example.hospital_roomdatabase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hospital_roomdatabase.model.PatientModel


@Dao
interface PatientDAO {

    @Insert
    suspend fun insert(patients: PatientModel)

    @Delete
    suspend fun delete(hospital: PatientModel)

    @Query("SELECT * FROM patients_table ORDER BY id ASC ")
    fun readAllData(): LiveData<List<PatientModel>>
}