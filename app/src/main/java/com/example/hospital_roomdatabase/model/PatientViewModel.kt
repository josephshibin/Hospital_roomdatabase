package com.example.hospital_roomdatabase.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hospital_roomdatabase.database.HospitalData
import com.example.hospital_roomdatabase.repository.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PatientViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<PatientModel>>
    private val repository: PatientRepository

    init {
        val patientDAO = HospitalData.getDatabase(application).patientsDAO()
        repository= PatientRepository(patientDAO)
        readAllData=repository.allPatients
    }
    fun insert(patient: PatientModel) = viewModelScope.launch(Dispatchers.IO){
       // val patient=PatientModel(0,patientName,patientGender,patientAddress,)
        repository.insert(patient)
    }
    fun delete(patient : PatientModel) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(patient)
    }
}