package com.example.hospital_roomdatabase.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query



// Dao is a interface which as all query methods
@Dao
interface HospitalDAO {

    @Insert()
    suspend fun insert(hospital:HospitalEntity)

    @Delete
    suspend fun delete(hospital:HospitalEntity)

    @Query("SELECT * FROM hospital_table ORDER BY id ASC ")
    fun readAllData():LiveData<List<HospitalEntity>>
}

@Dao
interface PatientsDAO {

    @Insert()
    suspend fun insert(patient: PatientsEntity)

    @Delete
    suspend fun delete(patient: PatientsEntity)

    @Query("SELECT * FROM patients_table ORDER BY id ASC ")
    fun readAllData(): LiveData<List<PatientsEntity>>
}