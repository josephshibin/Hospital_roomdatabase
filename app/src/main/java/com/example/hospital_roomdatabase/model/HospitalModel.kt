package com.example.hospital_roomdatabase.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospital_table")
data class HospitalModel(
    val hospitalName: String,
    val speciality: String,
    val location: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}



