package com.example.hospital_roomdatabase.fragment.edit

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.model.SharedViewModel

class UpdateFragment : Fragment() {
    lateinit var hospitalName:EditText
    lateinit var hospitalLocation:EditText
    lateinit var hospitalSpeciality:EditText
    lateinit var name:String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_edit, container, false)
        hospitalName=view.findViewById(R.id.editTextHospitalName)
        hospitalLocation=view.findViewById(R.id.editTextLocation)
        hospitalSpeciality=view.findViewById(R.id.editTextSpeciality)
        Log.i("hi","fdsf")

       //SharedViewModel (getting back the data)
        val  model = ViewModelProvider(this).get(SharedViewModel::class.java)
        model.currentHospitaldetails.observe(viewLifecycleOwner, Observer {
            hospitalName.setText(it.hospitalName)
            hospitalLocation.setText(it.location)
          hospitalSpeciality.setText(it.speciality)

            name=it.hospitalName.toString()
            Log.i("data",it.toString())


        })
      //  Log.i("hi","fdsffffffffd")


        return  view
    }

}