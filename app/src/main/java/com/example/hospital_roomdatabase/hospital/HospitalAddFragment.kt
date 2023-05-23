package com.example.hospital_roomdatabase.hospital

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.shared_viewmodel.HospitalViewModel
import com.example.hospital_roomdatabase.databinding.FragmentHospitalAddBinding
import com.example.hospital_roomdatabase.model.HospitalModel


class HospitalAddFragment : Fragment() {
    // initializing the view model
    private lateinit var hospitalViewModel: HospitalViewModel
    private lateinit var binding: FragmentHospitalAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHospitalAddBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        //using the view-model
        hospitalViewModel = ViewModelProvider(this)[HospitalViewModel::class.java]
        binding.buttonSave.setOnClickListener {
            addDataToDatabase()

        }

        return view
    }

    private fun addDataToDatabase() {
        val name = binding.editTextHospitalName.text.toString()
        val spec = binding.editTextSpeciality.text.toString()
        val loc = binding.editTextLocation.text.toString()

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