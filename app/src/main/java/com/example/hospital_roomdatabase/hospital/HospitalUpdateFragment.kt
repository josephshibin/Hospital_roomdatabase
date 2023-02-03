package com.example.hospital_roomdatabase.hospital

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
import com.example.hospital_roomdatabase.model.HospitalModel
import com.example.hospital_roomdatabase.model.HospitalViewModel
import com.example.hospital_roomdatabase.model.SharedViewModelForHospital
import kotlin.properties.Delegates


class HospitalUpdateFragment : Fragment() {
    lateinit var hospitalName:EditText
    lateinit var hospitalLocation:EditText
    lateinit var hospitalSpeciality:EditText
    lateinit var updateButton: Button


    private var currentHospitalDataPosition by Delegates.notNull<Int>()


    // intializing the view model
    private  lateinit var hospitalViewModel: HospitalViewModel




    //creating a instance of sharedView model class
    private val sharedViewModel: SharedViewModelForHospital by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_edit, container, false)
        hospitalName=view.findViewById(R.id.editTextHospitalName)
        hospitalLocation=view.findViewById(R.id.editTextLocation)
        hospitalSpeciality=view.findViewById(R.id.editTextSpeciality)
        updateButton=view.findViewById(R.id.buttonUpdate)
       // Log.i("hi","fdsf")

        //useing the viewmodel
        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)



        // getting data back from shared view model
        sharedViewModel.currentHospitalDetails.observe(viewLifecycleOwner, Observer { it->
            hospitalName.setText(it.hospitalName)
            hospitalLocation.setText(it.location)
            hospitalSpeciality.setText(it.speciality)
            //assigning  the value of id
            currentHospitalDataPosition=it.id


        })

        updateButton.setOnClickListener {
            updateHospitalDataToDataBase()
        }


        return  view
    }
    // Update functionality
    private fun updateHospitalDataToDataBase(){
        val name=hospitalName.text.toString()
        val spec=hospitalSpeciality.text.toString()
        val loc=hospitalLocation.text.toString()

        if(isCheck(name,spec,loc)){
            // creating HospitalModel object
            val updatedHospital=HospitalModel(name,spec,loc)
            //assigning the id of current item to new instance(updatedHospital)
            updatedHospital.id=currentHospitalDataPosition

            // adding data to database
            hospitalViewModel.update(updatedHospital)
            Toast.makeText(context,"data updated", Toast.LENGTH_SHORT).show()
            view?.findNavController()?.navigate(R.id.action_editFragment_to_hospitalFragment2)
        }
        else
        {
            Toast.makeText(context,"Please add the data", Toast.LENGTH_LONG).show()
        }
    }
    private fun isCheck(name:String, spec:String, loc:String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(spec) || TextUtils.isEmpty(loc))
    }

}