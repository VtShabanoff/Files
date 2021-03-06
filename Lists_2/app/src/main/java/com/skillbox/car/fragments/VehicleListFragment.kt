package com.skillbox.car.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.car.AutoClearedValue
import com.skillbox.car.VehicleListViewModel
import com.skillbox.car.`interface`.TransferringDate
import com.skillbox.car.adapter.VehicleAdapter
import com.skillbox.car.databinding.FragmentListVehicleBinding
import com.skillbox.car.dialog.DialogCreateVehicle
import jp.wasabeef.recyclerview.animators.ScaleInAnimator

class VehicleListFragment : Fragment(), TransferringDate {
    private var _binding: FragmentListVehicleBinding? = null
    private val binding: FragmentListVehicleBinding
        get() = _binding!!
    private var vehicleAdapter by AutoClearedValue<VehicleAdapter>()
    private val vehicleViewModel: VehicleListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addFAD.setOnClickListener {
            DialogCreateVehicle().show(childFragmentManager, "TAG")
        }
        initRecycleView()
        observeViewModelState()
    }

    private fun initRecycleView() {
        vehicleAdapter = VehicleAdapter { position ->
            deleteVehicle(position)
        }
        with(binding.vehicleList) {
            adapter = vehicleAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
        }
        binding.listEmptyTextView.isVisible = vehicleViewModel.getVehicles().isEmpty()
    }

    private fun deleteVehicle(position: Int) {
        vehicleViewModel.deleteVehicle(position)
        binding.listEmptyTextView.isVisible = vehicleViewModel.getVehicles().isEmpty()
    }

    private fun addVehicleToList() {
        vehicleViewModel.addVehicle()
        binding.vehicleList.scrollToPosition(0)
        binding.listEmptyTextView.isVisible = vehicleViewModel.getVehicles().isEmpty()
    }

    private fun observeViewModelState(){
        vehicleViewModel.vehicles.observe(viewLifecycleOwner){
            newVehicles -> vehicleAdapter.items = newVehicles
        }
    }

    override fun onTransferDate(inputModel: String, inputMake: String, type: Boolean) {
//        textModel = inputModel
//        textMake = inputMake
//        isElectric = type
        addVehicleToList()
        binding.listEmptyTextView.isVisible = vehicleViewModel.getVehicles().isEmpty()
    }


}