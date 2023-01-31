package com.example.hospital_roomdatabase.data

import androidx.lifecycle.LiveData


class HospitalRepository(private val hospitalDao : HospitalDAO) {

    val allHospitals : LiveData<List<HospitalEntity>> = hospitalDao.readAllData()


    suspend fun insert(hospitals :HospitalEntity){
        hospitalDao.insert(hospitals)
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