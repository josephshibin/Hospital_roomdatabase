package com.example.hospital_roomdatabase.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// line 12 is to add the entity's if we have more than one table then
//@Database(entities = [HospitalEntity::class,SecondEntity::class,etc], version = 1)
@Database(entities = [HospitalEntity::class,PatientsEntity::class], version = 1)
abstract class      HospitalData : RoomDatabase() {

    abstract fun hospitalDAO() : HospitalDAO
    abstract fun patientsDAO() : PatientsDAO

    //Singleton

    companion object{

        @Volatile
        private var INSTANCE : HospitalData? = null

        fun getDatabase(context : Context) : HospitalData{
            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(context.applicationContext,
                    HospitalData::class.java,"hospital_data")
                        // line 30 is for giving data while coding itself this line calls the function below
                    //.addCallback(HospitalDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance

            }
        }

    }

    // from 41 to 64 is the code for giving data while compling time itself
    //udemy
    //line 25 if we are adding data while codeing itesel then in line 25 add one more parameter (scope) and
    // and pass that parameter in line 31 make required changes in view model also

//    private class NoteDatabaseCallback(private val scope : CoroutineScope) : RoomDatabase.Callback(){
//
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//
//            INSTANCE?.let { database ->
//
//                //database.getNoteDao().insert(Note("t","d"))
//
//                scope.launch {
//
//                    val noteDao = database.getNoteDao()
//                 // giving data to room database
//                    noteDao.insert(Note("Title 1","Description 1"))
//                    noteDao.insert(Note("Title 2","Description 2"))
//                    noteDao.insert(Note("Title 3","Description 3"))
//
//                }
//
//            }
//
//        }
//
//    }

}