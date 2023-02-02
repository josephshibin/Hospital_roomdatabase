package com.example.hospital_roomdatabase.patient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.databinding.FragmentPatientDetailsBinding
import com.example.hospital_roomdatabase.model.SharedViewModelForPatient


class PatientsDetailsFragment : Fragment() {
    private lateinit var binding:FragmentPatientDetailsBinding
    private val sharedViewModel: SharedViewModelForPatient by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentPatientDetailsBinding.inflate(layoutInflater, container, false)
        val view=binding.root

//        sharedViewModel.currentPatientsDetails.observe(viewLifecycleOwner, Observer { it ->
//            binding.textViewPatientsName.text=it.patientName
//            binding.textViewPatientsGender.text=it.patientGender
//            binding.textViewPatientAddress.text=it.patientAddress
//        })




        return  view
    }


}