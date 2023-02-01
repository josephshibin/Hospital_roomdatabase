package com.example.hospital_roomdatabase.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hospital_roomdatabase.Database.HospitalEntity

class SharedViewModel :ViewModel() {
    val currentHospitaldetails= MutableLiveData<HospitalEntity>()


    fun setHospitalDetails(currentItem:HospitalEntity){
       currentHospitaldetails.value=currentItem
        Log.i("livedata", currentHospitaldetails.toString())
    }
}