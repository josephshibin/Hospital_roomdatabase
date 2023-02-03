package com.example.hospital_roomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize




@Entity(tableName = "patients_table"
    ,foreignKeys = [
        ForeignKey(
            entity = HospitalModel::class,
            parentColumns = ["id"],
            childColumns = ["hospitalId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )]
)


data class PatientModel (
    @PrimaryKey(autoGenerate = true)

    val id:Int,
    val patientName:String,
    val patientGender:String,
    val patientAddress:String,
    val hospitalId:Int

)