package com.example.hospital_roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_roomdatabase.R

import com.example.hospital_roomdatabase.model.PatientModel
import com.example.hospital_roomdatabase.patient.PatientsFragment


class PatientsAdapter(private val activity: PatientsFragment, val  setPatientInfo:(PatientModel)->Unit): RecyclerView.Adapter<PatientsAdapter.MyViewHolder>() {

    var patientsList = emptyList<PatientModel>()


    inner  class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var patientName: TextView =itemView.findViewById(R.id.textViewPatientsName)
        var cardView: CardView =itemView.findViewById(R.id.cardViewForPatients)






    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.patientlistview,parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return patientsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem=patientsList[position]
        holder.patientName.text=currentItem.patientName.toString()
        holder.cardView.setOnClickListener {

            setPatientInfo(currentItem)

            activity.findNavController().navigate(R.id.action_patientsFragment_to_patientDetailsFragment)

        }





    }


    fun setPatients(hospital:List<PatientModel>){
        this.patientsList=hospital
        notifyItemInserted( patientsList.size+1)
    }

    fun getPatients(position : Int) : PatientModel {


        return patientsList[position]

    }
}