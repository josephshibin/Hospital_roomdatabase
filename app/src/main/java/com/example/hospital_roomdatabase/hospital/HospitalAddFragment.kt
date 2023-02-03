package com.example.hospital_roomdatabase.hospital

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.model.HospitalModel
import com.example.hospital_roomdatabase.database.shared_viewmodel.HospitalViewModel


class HospitalAddFragment : Fragment() {
    // initializing the view model
    private lateinit var hospitalViewModel: HospitalViewModel

    //initializing the views
    private lateinit var hospitalName: EditText
    private lateinit var hospitalSpeciality: EditText
    private lateinit var hospitalLocation: EditText
    private lateinit var buttonSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hospital_add, container, false)


        hospitalName = view.findViewById(R.id.editTextHospitalName)
        hospitalSpeciality = view.findViewById(R.id.editTextSpeciality)
        hospitalLocation = view.findViewById(R.id.editTextLocation)
        buttonSave = view.findViewById(R.id.buttonSave)

        //using the view-model
        hospitalViewModel = ViewModelProvider(this)[HospitalViewModel::class.java]



        buttonSave.setOnClickListener {
            addDataToDatabase()

        }


        return view
    }

    private fun addDataToDatabase() {
        val name = hospitalName.text.toString()
        val spec = hospitalSpeciality.text.toString()
        val loc = hospitalLocation.text.toString()

        if (isCheck(name, spec, loc)) {
            // creating HospitalEntity object
            val hospital = HospitalModel(name, spec, loc)
            // adding data to database
            hospitalViewModel.insert(hospital)
            Toast.makeText(context, "data added", Toast.LENGTH_SHORT).show()
            view?.findNavController()
                ?.navigate(R.id.action_hospitalAddFragment_to_hospitalFragment2)
        } else {
            Toast.makeText(context, "Please add the data", Toast.LENGTH_LONG).show()
        }
    }

    private fun isCheck(name: String, spec: String, loc: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(spec) || TextUtils.isEmpty(loc))
    }
}