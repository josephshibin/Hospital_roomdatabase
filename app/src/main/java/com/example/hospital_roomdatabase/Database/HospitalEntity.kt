package com.example.hospital_roomdatabase.Database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// from 6 to 15
// here we create a  table to store data of hospitals
//line 12 and 21 is for safeargs
@Parcelize
@Entity(tableName = "hospital_table")
 data class HospitalEntity (
    @PrimaryKey(autoGenerate = true)
     val id:Int,
     val hospitalName:String,
     val speciality:String,
     val location:String

):Parcelable


@Entity(tableName = "patients_table")
data class PatientsEntity (
 @PrimaryKey(autoGenerate = true)
 val id:Int,
 val patientName:String,
 val patientGender:String,
 val patientAddress:String,

)
