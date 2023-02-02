package com.example.hospital_roomdatabase.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModelForHospital :ViewModel() {
    private  val _currentHospitalDetails= MutableLiveData<HospitalModel>()
    val currentHospitalDetails: LiveData<HospitalModel> =_currentHospitalDetails


    fun setHospitalDetails(currentItem:HospitalModel){
        _currentHospitalDetails.value=currentItem
        // Log.i("livedata", currentHospitalDetails.toString())
    }
}

class SharedViewModelForPatient :ViewModel() {
    private  val _currentPatientsDetails= MutableLiveData<PatientModel>()
    val currentPatientsDetails: LiveData<PatientModel> =_currentPatientsDetails


    fun setPatientsDetails(currentItem:PatientModel){
        _currentPatientsDetails.value=currentItem
        // Log.i("livedata", currentHospitalDetails.toString())
    }
}