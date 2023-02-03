package com.example.hospital_roomdatabase.hospital

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.adapter.HospitalAdapter
import com.example.hospital_roomdatabase.model.HospitalModel
import com.example.hospital_roomdatabase.model.HospitalViewModel
import com.example.hospital_roomdatabase.model.SharedViewModelForHospital


class HospitalFragment : Fragment() {

    // intializing the view model
    private lateinit var hospitalViewModel: HospitalViewModel

    private lateinit var recyclerView: RecyclerView


    // initializing shared view model
    private val sharedViewModel: SharedViewModelForHospital by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hospital, container, false)

//        //RecyclerView
        val adapter = HospitalAdapter(this) { index -> setHospitalInfo(index) }
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter


        //hospitalViewModel (database)
        //useing the viewmodel
        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)
        hospitalViewModel.readAllData.observe(viewLifecycleOwner, Observer { hospitals ->
            adapter.setHospital(hospitals)
        })


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

                hospitalViewModel.delete(adapter.getHospital(viewHolder.adapterPosition))
                adapter.notifyItemRemoved(viewHolder.adapterPosition)


            }

        }).attachToRecyclerView(recyclerView)





        setHasOptionsMenu(true)
        return view
    }


    // below to over ride function is to view the menu option
    // on the action bar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.hospitalAddFragment -> {
                view?.findNavController()
                    ?.navigate(R.id.action_hospitalFragment2_to_hospitalAddFragment)
            }
            R.id.deleteAll -> showDialogMessage()

        }

        return true
    }

    private fun showDialogMessage() {
        val dialogMessage = AlertDialog.Builder(requireContext())
        dialogMessage.setTitle("Delete All Hospital")
        dialogMessage.setMessage(
            "If click Yes all Hospitals will delete" +
                    ", if you want to delete a specific Hospitals, please swipe left or right."
        )
        dialogMessage.setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->

            dialog.cancel()

        })
        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->

            hospitalViewModel.deleteAllHospitals()

        })

        dialogMessage.create().show()

    }


    private fun setHospitalInfo(currentItemToEdit: HospitalModel) {
        sharedViewModel.setHospitalDetails(currentItemToEdit)
    }


}