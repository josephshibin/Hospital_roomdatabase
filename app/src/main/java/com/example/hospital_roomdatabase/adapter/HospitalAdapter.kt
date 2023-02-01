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
import com.example.hospital_roomdatabase.Database.HospitalEntity
import com.example.hospital_roomdatabase.fragment.list.HospitalFragment

class HospitalAdapter(private val activity: HospitalFragment,val editItemInData:(Int)-> Unit):RecyclerView.Adapter<HospitalAdapter.MyViewHolder>() {

   var hospitalList = emptyList<HospitalEntity>()


    inner  class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var hospitalName:TextView=itemView.findViewById(R.id.textViewHospitalName)
        var hospitalSpeciality:TextView=itemView.findViewById(R.id.textViewHospitalSpeciality)
        var editButton:Button=itemView.findViewById(R.id.buttonEdit)
        var cardView:CardView=itemView.findViewById(R.id.materialCardView)


        fun editItem(position: Int){
            editButton.setOnClickListener {
                //            // sharedViewModel
//            val model = ViewModelProvider(activity).get(SharedViewModel::class.java)
//            model.setHospitalDetails(hospitalList[position])
                editItemInData(position)



                // navigation action
            activity.findNavController().navigate(R.id.action_hospitalFragment2_to_editFragment)
            }

        }

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
        holder.editItem(position)

//        holder.editButton.setOnClickListener {
//
//            // sharedViewModel
//            val model = ViewModelProvider(activity).get(SharedViewModel::class.java)
//            model.setHospitalDetails(currentItem)
//
//           // navigation action
//            activity.findNavController().navigate(R.id.action_hospitalFragment2_to_editFragment)
//        }
        holder.cardView.setOnClickListener {
            activity.findNavController().navigate(R.id.action_hospitalFragment2_to_patientsFragment)
        }
    }


    fun setData(hospitals:List<HospitalEntity>){
        this.hospitalList=hospitals
        notifyItemInserted( hospitalList.size+1)
    }
    fun getHospital(position : Int) : HospitalEntity{

        return hospitalList[position]

    }
}