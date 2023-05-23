package com.example.hospital_roomdatabase.shared_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hospital_roomdatabase.model.HospitalModel
import com.example.hospital_roomdatabase.model.PatientModel

class SharedViewModelForHospital : ViewModel() {
    private val _currentHospitalDetails = MutableLiveData<HospitalModel>()
    val currentHospitalDetails: LiveData<HospitalModel> = _currentHospitalDetails
    fun setHospitalDetails(currentItem: HospitalModel) {
        _currentHospitalDetails.value = currentItem
    }
}

class SharedViewModelForPatient : ViewModel() {
    private val _currentPatientsDetails = MutableLiveData<PatientModel>()
    val currentPatientsDetails: LiveData<PatientModel> = _currentPatientsDetails

    fun setPatientsDetails(currentItem: PatientModel) {
        _currentPatientsDetails.value = currentItem
    }
}