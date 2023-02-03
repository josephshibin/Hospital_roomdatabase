package com.example.hospital_roomdatabase.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hospital_roomdatabase.database.HospitalData
import com.example.hospital_roomdatabase.repository.HospitalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HospitalViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<HospitalModel>>
    private val repository: HospitalRepository

    init {
        val hospitalDAO = HospitalData.getDatabase(application).hospitalDAO()
        repository = HospitalRepository(hospitalDAO)
        readAllData = repository.allHospitals
    }

    fun insert(hospital: HospitalModel) = viewModelScope.launch(Dispatchers.IO) {
        // val hospital=HospitalModel(0,hospitalName,hospitalSpeciality,hospitalLocation)
        repository.insert(hospital)
    }

    fun delete(hospital: HospitalModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(hospital)
    }

    fun deleteAllHospitals() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAllHospitals()
    }

    fun update(hospital: HospitalModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(hospital)
    }
}


