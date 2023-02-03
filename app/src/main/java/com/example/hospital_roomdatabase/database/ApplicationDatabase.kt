package com.example.hospital_roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hospital_roomdatabase.model.HospitalModel
import com.example.hospital_roomdatabase.model.PatientModel

// line 12 is to add the entity's if we have more than one table then
//@Database(entities = [HospitalEntity::class,SecondEntity::class,etc], version = 1)
@Database(entities = [HospitalModel::class, PatientModel::class], version = 1)
abstract class HospitalData : RoomDatabase() {

    abstract fun hospitalDAO(): HospitalDAO
    abstract fun patientsDAO(): PatientDAO

    //Singleton

    companion object {

        @Volatile
        private var INSTANCE: HospitalData? = null

        fun getDatabase(context: Context): HospitalData {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HospitalData::class.java, "hospital_data"
                )
                    // line 30 is for giving data while coding itself this line calls the function below
                    //.addCallback(HospitalDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance

            }
        }

    }
}