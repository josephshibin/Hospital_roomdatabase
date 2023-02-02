package com.example.hospital_roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.hospital_roomdatabase.database.PatientDAO
import com.example.hospital_roomdatabase.model.PatientModel


class PatientRepository(private val patientsDao : PatientDAO) {

    val allPatients: LiveData<List<PatientModel>> = patientsDao.readAllData()


    suspend fun insert(patients: PatientModel) {
        patientsDao.insert(patients)
    }

    suspend fun delete(patients : PatientModel){
        patientsDao.delete(patients)
    }
}
