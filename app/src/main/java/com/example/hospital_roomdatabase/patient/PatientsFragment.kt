package com.example.hospital_roomdatabase.patient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.adapter.PatientsAdapter
import com.example.hospital_roomdatabase.model.*


class PatientsFragment : Fragment() {

    // intializing the view model
    private  lateinit var patientsViewModel: PatientViewModel



    private lateinit var recyclerView: RecyclerView
    private lateinit var hospitalSpeciality: TextView
    private lateinit var  hospitalLocation:TextView
    private lateinit var addPatient:Button
    private lateinit var titleOfThisFragment:String

    //creating a instance of sharedViewmodel class
    private val sharedViewModelForHospital: SharedViewModelForHospital by activityViewModels()
    private val sharedViewModelForPatient: SharedViewModelForPatient by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_patients, container, false)

        hospitalLocation=view.findViewById(R.id.textViewHospitalLocation)
        hospitalSpeciality=view.findViewById(R.id.textViewHospitalSpeciality)
        addPatient=view.findViewById(R.id.buttonAdd)
        addPatient.setOnClickListener {
            view.findNavController().navigate(R.id.action_patientsFragment_to_patientsAddFragment)
        }
        //RecyclerView
        val adapter=PatientsAdapter(this){currentItem -> setPatientInfo(currentItem)}
        recyclerView=view.findViewById(R.id.recyclerViewOfPatients)
        recyclerView.layoutManager = LinearLayoutManager(context);
        recyclerView.adapter=adapter


        //patientViewModel (database)
        //useing the viewmodel
        patientsViewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
       patientsViewModel.readAllData.observe(viewLifecycleOwner, Observer {
           adapter.setData(it)
       })


        // get data from shared view model
        sharedViewModelForHospital.currentHospitalDetails.observe(viewLifecycleOwner, Observer { it->
            hospitalLocation.text=it.location.toString()
            hospitalSpeciality.text=it.speciality.toString()
            titleOfThisFragment=it.hospitalName.toString()
            (activity as AppCompatActivity).supportActionBar?.title=titleOfThisFragment
        })





        // delete by sliding
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0
            , ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                patientsViewModel .delete(adapter.getPatients(viewHolder.adapterPosition))
                adapter.notifyItemRemoved(viewHolder.adapterPosition)

            }

        }).attachToRecyclerView(recyclerView)

        return  view
    }


    private fun setPatientInfo(currentItem: PatientModel) {
        sharedViewModelForPatient.setPatientsDetails(currentItem)
    }

}