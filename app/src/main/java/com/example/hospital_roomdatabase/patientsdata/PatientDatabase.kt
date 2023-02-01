//package com.example.hospital_roomdatabase.patientsdata
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//
//@Database(entities = [PatientsEntity::class], version = 1)
//abstract class      PatientDatabase : RoomDatabase() {
//
//    abstract fun patientsDAO() :PatientsDAO
//
//    //Singleton
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: PatientDatabase? = null
//
//        fun getDatabase(context: Context): PatientDatabase {
//            return INSTANCE ?: synchronized(this) {
//
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    PatientDatabase::class.java, "patient_database"
//                )
//                    // line 30 is for giving data while coding itself this line calls the function below
//                    //.addCallback(HospitalDatabaseCallback(scope)) if required
//                    .build()
//
//                INSTANCE = instance
//
//                instance
//
//            }
//        }
//
//
//    }
//}
