package com.example.hospital_roomdatabase.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hospital_roomdatabase.model.HospitalModel


// Dao is a interface which as all query methods
@Dao
interface HospitalDAO {

    @Insert()
    suspend fun insert(hospital: HospitalModel)

    @Delete
    suspend fun delete(hospital: HospitalModel)

    @Update
    suspend fun update(hospital: HospitalModel)

    @Query("DELETE FROM hospital_table")
    suspend fun deleteAllHospitals()

    @Query("SELECT * FROM hospital_table ORDER BY id ASC ")
    fun readAllData():LiveData<List<HospitalModel>>
}

