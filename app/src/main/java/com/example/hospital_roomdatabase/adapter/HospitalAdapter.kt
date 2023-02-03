package com.example.hospital_roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.hospital.HospitalFragment
import com.example.hospital_roomdatabase.model.HospitalModel

class HospitalAdapter(
    private val activity: HospitalFragment,
    val setHospitalInfo: (HospitalModel) -> Unit
) : RecyclerView.Adapter<HospitalAdapter.MyViewHolder>() {

    private var hospitalList = emptyList<HospitalModel>()


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var hospitalName: TextView = itemView.findViewById(R.id.textViewHospitalName)
        var hospitalSpeciality: TextView = itemView.findViewById(R.id.textViewHospitalSpeciality)
        var editButton: Button = itemView.findViewById(R.id.buttonEdit)
        var cardView: CardView = itemView.findViewById(R.id.materialCardView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.hospitalitemview, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = hospitalList[position]
        holder.hospitalName.text = currentItem.hospitalName
        holder.hospitalSpeciality.text = currentItem.speciality
        holder.editButton.setOnClickListener {
            setHospitalInfo(currentItem)
            activity.findNavController().navigate(R.id.action_hospitalFragment2_to_editFragment)
        }


        holder.cardView.setOnClickListener {
            setHospitalInfo(currentItem)
            activity.findNavController().navigate(R.id.action_hospitalFragment2_to_patientsFragment)
        }
    }


    fun setHospital(hospitals: List<HospitalModel>) {
        this.hospitalList = hospitals
        notifyDataSetChanged()
    }

    fun getHospital(position: Int): HospitalModel {


        return hospitalList[position]

    }
}