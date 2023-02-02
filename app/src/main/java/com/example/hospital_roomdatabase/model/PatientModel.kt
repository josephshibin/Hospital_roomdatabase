package com.example.hospital_roomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize




@Parcelize
@Entity(tableName = "patients_table")
data class PatientModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val patientName:String,
    val patientGender:String,
    val patientAddress:String

): Parcelable