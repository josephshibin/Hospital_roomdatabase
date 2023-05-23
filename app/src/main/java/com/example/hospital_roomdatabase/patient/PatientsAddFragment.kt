package com.example.hospital_roomdatabase.patient


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.shared_viewmodel.PatientViewModel
import com.example.hospital_roomdatabase.shared_viewmodel.SharedViewModelForHospital
import com.example.hospital_roomdatabase.databinding.FragmentPatientsAddBinding
import com.example.hospital_roomdatabase.model.PatientModel
import kotlin.properties.Delegates

class PatientsAddFragment : Fragment() {

    //creating a instance of sharedView model class
    private val sharedViewModelForHospital: SharedViewModelForHospital by activityViewModels()
    private var hospitalId by Delegates.notNull<Int>()
    private lateinit var patientViewModel: PatientViewModel
    private lateinit var binding: FragmentPatientsAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPatientsAddBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        //getting the hospital id from shareholder of hospital
        sharedViewModelForHospital.currentHospitalDetails.observe(viewLifecycleOwner) {
            hospitalId = it.id
        }
        patientViewModel = ViewModelProvider(this)[PatientViewModel::class.java]

        binding.buttonPatientsAdd.setOnClickListener {
            addDataToDatabase()
        }
        return view
    }

    private fun addDataToDatabase() {
        val name = binding.editTextPatientsName.text.toString()
        val gender = binding.editTextGender.text.toString()
        val address = binding.editTextAddress.text.toString()

        if (isCheck(name, gender, address)) {
            // creating HospitalEntity object
            val patient = PatientModel(0, name, gender, address, hospitalId)
            // adding data to database
            patientViewModel.insert(patient)
            Toast.makeText(context, "data added", Toast.LENGTH_SHORT).show()
            view?.findNavController()?.navigate(R.id.action_patientsAddFragment_to_patientsFragment)
        } else {
            Toast.makeText(context, "Please add the data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isCheck(name: String, gender: String, address: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(gender) || TextUtils.isEmpty(address))
    }
}


