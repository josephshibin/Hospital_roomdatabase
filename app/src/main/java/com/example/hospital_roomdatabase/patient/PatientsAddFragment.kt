package com.example.hospital_roomdatabase.patient

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.model.PatientModel


import com.example.hospital_roomdatabase.model.PatientViewModel
import com.example.hospital_roomdatabase.model.SharedViewModelForHospital

class PatientsAddFragment : Fragment() {

    //creating a instance of sharedViewmodel class
    private val sharedViewModelForHospital: SharedViewModelForHospital by activityViewModels()
    var hospitalId=-1

    private  lateinit var patientViewModel: PatientViewModel

    private lateinit var  patientName:EditText
    lateinit var  patientGender:EditText
    lateinit var  patientAddress:EditText
    lateinit var  addButton:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_patients_add, container, false)
        patientName=view.findViewById(R.id.editTextPatientsName)
        patientGender=view.findViewById(R.id.editTextGender)
        patientAddress=view.findViewById(R.id.editTextAddress)
        addButton=view.findViewById(R.id.buttonPatientsAdd)

        //geting the hospital id from sharedviewmodel of hospital
        sharedViewModelForHospital.currentHospitalDetails.observe(viewLifecycleOwner, Observer { it->
            hospitalId=it.id
        })



        patientViewModel = ViewModelProvider(this).get(PatientViewModel::class.java)

        addButton.setOnClickListener {
            addDataToDatabase()

        }

        return  view
    }
    private fun addDataToDatabase(){
        val name=patientName.text.toString()
        val gender=patientGender.text.toString()
        val address= patientAddress.text.toString()

        if(isCheck(name,gender,address)){
            // createing HospitalEntity object
             val patient= PatientModel(0,name,gender,address,hospitalId)
            // adding data to database
            patientViewModel.insert(patient)
            Toast.makeText(context,"data added", Toast.LENGTH_SHORT).show()
            view?.findNavController()?.navigate(R.id.action_patientsAddFragment_to_patientsFragment)
        }
        else
        {
            Toast.makeText(context,"Please add the data", Toast.LENGTH_SHORT).show()
        }
    }
    private fun isCheck(name:String, gender:String, address:String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(gender) && TextUtils.isEmpty(address))
    }
}


