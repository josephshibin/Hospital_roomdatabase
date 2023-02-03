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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.model.HospitalModel
import com.example.hospital_roomdatabase.model.HospitalViewModel


class HospitalAddFragment : Fragment() {
    // intializing the view model
    private  lateinit var hospitalViewModel: HospitalViewModel




    //intializing the views
    lateinit var hospitalName:EditText
    lateinit var hospitalSpeciality:EditText
    lateinit var hospitalLocation:EditText
    lateinit var buttonSave:Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_hospital_add, container, false)


        hospitalName=view.findViewById(R.id.editTextHospitalName)
        hospitalSpeciality=view.findViewById(R.id.editTextSpeciality)
        hospitalLocation=view.findViewById(R.id.editTextLocation)
        buttonSave=view.findViewById(R.id.buttonSave)



        //useing the viewmodel
        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)



        buttonSave.setOnClickListener {
            addDataToDatabase()

        }


        return  view
    }
  private fun addDataToDatabase(){
      val name=hospitalName.text.toString()
      val spec=hospitalSpeciality.text.toString()
      val loc=hospitalLocation.text.toString()

      if(isCheck(name,spec,loc)){
          // createing HospitalEntity object
          val hospital= HospitalModel(name,spec,loc)
          // adding data to database
          hospitalViewModel.insert(hospital)
          Toast.makeText(context,"data added",Toast.LENGTH_SHORT).show()
          view?.findNavController()?.navigate(R.id.action_hospitalAddFragment_to_hospitalFragment2)
      }
      else
      {
          Toast.makeText(context,"Please add the data",Toast.LENGTH_LONG).show()
      }
  }
  private fun isCheck(name:String, spec:String, loc:String): Boolean {
      return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(spec) || TextUtils.isEmpty(loc))
  }
}