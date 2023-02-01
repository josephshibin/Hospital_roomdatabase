package com.example.hospital_roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.Database.PatientsEntity




class PatientsAdapter(): RecyclerView.Adapter<PatientsAdapter.MyViewHolder>() {

    var patientsList = emptyList<PatientsEntity>()


    inner  class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var patientName: TextView =itemView.findViewById(R.id.textViewPatientsName)






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





    }


    fun setData(hospital:List<PatientsEntity>){
        this.patientsList=hospital
        notifyItemInserted( patientsList.size+1)
    }

    fun getPatients(position : Int) : PatientsEntity {

        return patientsList[position]

    }
}