package com.example.hospital_roomdatabase.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HospitalViewModel(application: Application) : AndroidViewModel(application) {
     val readAllData:LiveData<List<HospitalEntity>>
    private val repository:HospitalRepository

    init {
        val hospitalDAO = HospitalDatabase.getDatabase(application).hospitalDAO()
        repository=HospitalRepository(hospitalDAO)
        readAllData=repository.allHospitals
    }
    fun insert(hospital : HospitalEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(hospital)
    }
}