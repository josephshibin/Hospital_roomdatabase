package com.example.hospital_roomdatabase.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.hospital_roomdatabase.database.HospitalDAO
import com.example.hospital_roomdatabase.model.HospitalModel


class HospitalRepository(private val hospitalDao: HospitalDAO) {

     val allHospitals: LiveData<List<HospitalModel>> = hospitalDao.readAllData()


    suspend fun insert(hospital: HospitalModel) {
        hospitalDao.insert(hospital)
    }

    @WorkerThread
    suspend fun delete(hospital: HospitalModel) {
        hospitalDao.delete(hospital)
    }


    suspend fun update(hospital: HospitalModel){
        hospitalDao .update(hospital)
    }


//    suspend fun delete(note : Note){
//        hospitalDao .delete(note)
//    }


//    suspend fun deleteAllNotes(){
//        hospitalDao .deleteAllNotes()
//    }

}


