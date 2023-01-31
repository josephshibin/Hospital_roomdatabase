package com.example.hospital_roomdatabase.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.data.HospitalEntity
import com.example.hospital_roomdatabase.fragment.edit.UpdateFragment
import com.example.hospital_roomdatabase.fragment.list.HospitalFragment
import com.example.hospital_roomdatabase.model.SharedViewModel

class HospitalAdapter(private val activity: HospitalFragment):RecyclerView.Adapter<HospitalAdapter.MyViewHolder>() {

    private var hospitalList = emptyList<HospitalEntity>()

    inner  class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var hospitalName:TextView=itemView.findViewById(R.id.textViewHospitalName)
        var hospitalSpeciality:TextView=itemView.findViewById(R.id.textViewHospitalSpeciality)
        var editButton:Button=itemView.findViewById(R.id.buttonEdit)
        var cardView:CardView=itemView.findViewById(R.id.materialCardView)

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.hospitalitemview,parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  hospitalList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem=hospitalList[position]
        holder.hospitalName.text=currentItem.hospitalName.toString()
        holder.hospitalSpeciality.text=currentItem.speciality.toString()

        holder.editButton.setOnClickListener {

            // sharedViewModel
            val model = ViewModelProvider(activity).get(SharedViewModel::class.java)
            model.setHospitalDetails(currentItem)

           // navigation action
            activity.findNavController().navigate(R.id.action_hospitalFragment2_to_editFragment)
        }
        holder.cardView.setOnClickListener {
            activity.findNavController().navigate(R.id.action_hospitalFragment2_to_patientsFragment)
        }
    }


    fun setData(hospital:List<HospitalEntity>){
        this.hospitalList=hospital
        notifyItemInserted( hospitalList.size+1)
    }
}