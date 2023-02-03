package com.example.hospital_roomdatabase.hospital

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital_roomdatabase.R
import com.example.hospital_roomdatabase.adapter.HospitalAdapter
import com.example.hospital_roomdatabase.database.shared_viewmodel.HospitalViewModel
import com.example.hospital_roomdatabase.database.shared_viewmodel.SharedViewModelForHospital
import com.example.hospital_roomdatabase.databinding.FragmentHospitalBinding
import com.example.hospital_roomdatabase.model.HospitalModel


class HospitalFragment : Fragment() {
    // initializing the view model
    private lateinit var binding: FragmentHospitalBinding
    private lateinit var hospitalViewModel: HospitalViewModel

    // initializing shared view model
    private val sharedViewModel: SharedViewModelForHospital by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHospitalBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        //RecyclerView
        val adapter = HospitalAdapter(this) { index -> setHospitalInfo(index) }
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        //hospitalViewModel (database)
        //using the view-model
        hospitalViewModel = ViewModelProvider(this)[HospitalViewModel::class.java]
        hospitalViewModel.readAllData.observe(viewLifecycleOwner) { hospitals ->
            adapter.setHospital(hospitals)
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

                hospitalViewModel.delete(adapter.getHospital(viewHolder.adapterPosition))
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }

        }).attachToRecyclerView(recyclerView)
        setHasOptionsMenu(true)
        return view
    }

    // below to over ride function is to view the menu option
    // on the action bar
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    @Deprecated("Deprecated in Java")
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
        dialogMessage.setNegativeButton("No") { dialog, _ ->

            dialog.cancel()

        }
        dialogMessage.setPositiveButton("Yes") { _, _ ->

            hospitalViewModel.deleteAllHospitals()
        }
        dialogMessage.create().show()
    }

    private fun setHospitalInfo(currentItemToEdit: HospitalModel) {
        sharedViewModel.setHospitalDetails(currentItemToEdit)
    }
}