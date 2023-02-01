package com.example.hospital_roomdatabase.Database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class HospitalRepository(private val hospitalDao: HospitalDAO) {

     val allHospitals: LiveData<List<HospitalEntity>> = hospitalDao.readAllData()


    suspend fun insert(hospitals: HospitalEntity) {
        hospitalDao.insert(hospitals)
    }

    @WorkerThread
    suspend fun delete(hospitals: HospitalEntity) {
        hospitalDao.delete(hospitals)
    }


//    suspend fun update(note : Note){
//        hospitalDao .update(note)
//    }


//    suspend fun delete(note : Note){
//        hospitalDao .delete(note)
//    }


//    suspend fun deleteAllNotes(){
//        hospitalDao .deleteAllNotes()
//    }

}


class PatientRepository(private val patientsDao: PatientsDAO) {

    val allPatients: LiveData<List<PatientsEntity>> = patientsDao.readAllData()


    suspend fun insert(patients: PatientsEntity) {
        patientsDao.insert(patients)
    }

    suspend fun delete(patients: PatientsEntity) {
        patientsDao.delete(patients)
    }
}