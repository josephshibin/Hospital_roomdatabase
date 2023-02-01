package com.example.hospital_roomdatabase.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HospitalViewModel(application: Application) : AndroidViewModel(application) {
     val readAllData:LiveData<List<HospitalEntity>>
    private val repository:HospitalRepository

    init {
        val hospitalDAO = HospitalData.getDatabase(application).hospitalDAO()
        repository=HospitalRepository(hospitalDAO)
        readAllData=repository.allHospitals
    }
    fun insert(hospital : HospitalEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(hospital)
    }

    fun delete(hospital : HospitalEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(hospital)
    }
}


class PatientViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<PatientsEntity>>
    private val repository: PatientRepository

    init {
        val patientDAO = HospitalData.getDatabase(application).patientsDAO()
        repository= PatientRepository(patientDAO)
        readAllData=repository.allPatients
    }
    fun insert(patient : PatientsEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(patient)
    }
    fun delete(patient : PatientsEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(patient)
    }
}