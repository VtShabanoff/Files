package com.skillbox.car.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.car.AutoClearedValue
import com.skillbox.car.R
import com.skillbox.car.VehicleListViewModel
import com.skillbox.car.adapter.VehicleAdapter
import com.skillbox.car.databinding.FragmentListVehicleBinding
import com.skillbox.car.getNavigationResult
import jp.wasabeef.recyclerview.animators.ScaleInAnimator

class VehicleListFragment : Fragment(){
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
        binding.listEmptyTextView.isVisible = true
        binding.addFAD.setOnClickListener {
            val dialog =
               VehicleListFragmentDirections.actionVehicleListFragmentToDialogCreateVehicle()
            findNavController().navigate(dialog)
        }
        getBackStackEntryArgs()
        initRecycleView()
        observeViewModelState()
    }

    private fun initRecycleView() {
        vehicleAdapter = VehicleAdapter({ id ->
            deleteVehicle(id)
        },{
            findNavController().navigate(R.id.action_vehicleListFragment_to_detailedFragment)
        })

        with(binding.vehicleList) {
            adapter = vehicleAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
        }

    }

    private fun deleteVehicle(position: Long) {
        vehicleViewModel.deleteVehicle(position)
    }

    private fun observeViewModelState(){
        vehicleViewModel.vehicles.observe(viewLifecycleOwner){
                newVehicles -> vehicleAdapter.items = newVehicles
            binding.listEmptyTextView.isVisible = vehicleViewModel.getVehicles().isEmpty()
        }
        vehicleViewModel.showToastLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "Элемент удален", Toast.LENGTH_LONG).show()
        }

    }

    private fun getBackStackEntryArgs(){
        val navController = findNavController()
        val navBackStackEntry = navController.getBackStackEntry(R.id.vehicleListFragment)

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME
                && navBackStackEntry.savedStateHandle.contains(KEY_ARGS_MODEL_CAR)){

                    val modelCar = getNavigationResult<String>(KEY_ARGS_MODEL_CAR)
                        ?: throw Exception("no model args")
                    val makeCar = getNavigationResult<String>(KEY_ARGS_MAKE_CAR)
                        ?: throw Exception("no make args")
                    val isElectricCar = getNavigationResult<Boolean>(KEY_ARGS_IS_ELECTRIC_CAR)
                        ?: throw Exception("no isElectric args")
                    vehicleViewModel.addVehicle(modelCar, makeCar, isElectricCar)
                Log.d("MyTag", "model=$makeCar")

            }
            if (event == Lifecycle.Event.ON_CREATE){
                navBackStackEntry.savedStateHandle.remove<String>(KEY_ARGS_MODEL_CAR)


                Log.d("TAG", "vehicles = ${vehicleViewModel.getVehicles()}")
            }
        }
        navBackStackEntry.lifecycle.addObserver(observer)


        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                navBackStackEntry.lifecycle.removeObserver(observer)
                navBackStackEntry.savedStateHandle.remove<String>(KEY_ARGS_MODEL_CAR)
            }
        })
    }
    companion object{
        const val KEY_ARGS_MODEL_CAR = "key_args_model_car"
        const val KEY_ARGS_MAKE_CAR = "key_args_make_car"
        const val KEY_ARGS_IS_ELECTRIC_CAR = "key_args_is_electric_car"
    }
}


