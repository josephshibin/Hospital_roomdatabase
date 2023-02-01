package com.example.hospital_roomdatabase.patientsdata

//
//class PatientViewModel(application: Application) : AndroidViewModel(application) {
//    val readAllData: LiveData<List<PatientsEntity>>
//    private val repository: PatientRepository
//
//    init {
//        val patientDAO = PatientDatabase.getDatabase(application).patientsDAO()
//        repository= PatientRepository(patientDAO)
//        readAllData=repository.allPatients
//    }
//    fun insert(patient : PatientsEntity) = viewModelScope.launch(Dispatchers.IO){
//        repository.insert(patient)
//    }
//    fun delete(patient : PatientsEntity) = viewModelScope.launch(Dispatchers.IO){
//        repository.delete(patient)
//    }
//}