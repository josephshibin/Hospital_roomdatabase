package com.example.hospital_roomdatabase.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.adapter.PatientsAdapter
import com.example.hospital_roomdatabase.shared_viewmodel.PatientViewModel
import com.example.hospital_roomdatabase.shared_viewmodel.SharedViewModelForHospital
import com.example.hospital_roomdatabase.shared_viewmodel.SharedViewModelForPatient
import com.example.hospital_roomdatabase.databinding.FragmentPatientsBinding
import com.example.hospital_roomdatabase.model.PatientModel
import kotlin.properties.Delegates


class PatientsFragment : Fragment() {

    // initializing the view model
    private lateinit var patientsViewModel: PatientViewModel
    private lateinit var binding: FragmentPatientsBinding
    private lateinit var titleOfThisFragment: String
    private var hospitalId by Delegates.notNull<Int>()

    //creating a instance of sharedView model class
    private val sharedViewModelForHospital: SharedViewModelForHospital by activityViewModels()
    private val sharedViewModelForPatient: SharedViewModelForPatient by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentPatientsBinding.inflate(layoutInflater, container, false)
        val view = binding.root


        val recyclerView = binding.recyclerViewOfPatients
        binding.buttonAdd.setOnClickListener {
            view.findNavController().navigate(R.id.action_patientsFragment_to_patientsAddFragment)
        }
        //RecyclerView
        val adapter = PatientsAdapter(this) { currentItem -> setPatientInfo(currentItem) }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        //patientViewModel (database)
        //using the view-model
        patientsViewModel = ViewModelProvider(this)[PatientViewModel::class.java]
        patientsViewModel.readAllData.observe(viewLifecycleOwner) { it ->
            //adapter.setPatients(it)
            //creating a filtered list of patients to match with hospital id
            val filteredListOfPatients = it.filter { it.hospitalId == hospitalId }
            adapter.setPatients(filteredListOfPatients)
        }


        // get data from shared view model
        sharedViewModelForHospital.currentHospitalDetails.observe(
            viewLifecycleOwner
        ) {
            hospitalId = it.id
            binding.textViewHospitalLocation.text = it.location
            binding.textViewHospitalSpeciality.text = it.speciality
            titleOfThisFragment = it.hospitalName
            (activity as AppCompatActivity).supportActionBar?.title = titleOfThisFragment
        }

        // delete by sliding
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                patientsViewModel.delete(adapter.getPatients(viewHolder.adapterPosition))
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }

        }).attachToRecyclerView(recyclerView)
        return view
    }

    private fun setPatientInfo(currentItem: PatientModel) {
        sharedViewModelForPatient.setPatientsDetails(currentItem)
    }
}