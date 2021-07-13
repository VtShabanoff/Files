package com.skillbox.car.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.car.AutoClearedValue
import com.skillbox.car.`interface`.TransferringDate
import com.skillbox.car.adapter.VehicleAdapter
import com.skillbox.car.data_class.Vehicle
import com.skillbox.car.databinding.FragmentListVehicleBinding
import com.skillbox.car.dialog.DialogCreateVehicle
import java.lang.Exception
import kotlin.collections.ArrayList

class VehicleListFragment : Fragment(), TransferringDate {
    private var _binding: FragmentListVehicleBinding? = null
    private val binding: FragmentListVehicleBinding
        get() = _binding!!
    private var vehicleAdapter by AutoClearedValue<VehicleAdapter>()

    private var vehicles: List<Vehicle> = arrayListOf()

    private lateinit var textModel: String
    private lateinit var textMake: String
    private var isElectric = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null){
            vehicles = savedInstanceState.getParcelableArrayList(KEY_VEHICLES)
                ?: throw Exception("vehicles = null")
            binding.listEmptyTextView.isVisible = vehicles.isEmpty()
            Log.d("TAG", "if (savedInstanceState != null) -> vehicles.size = ${vehicles.size}")
        }

        binding.addFAD.setOnClickListener {
            DialogCreateVehicle().show(childFragmentManager, "TAG")
        }
        initRecycleView()

        vehicleAdapter.updateVehicle(vehicles)
        vehicleAdapter.notifyDataSetChanged()


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(KEY_VEHICLES, vehicles as ArrayList)
    }

    private fun initRecycleView() {
        vehicleAdapter = VehicleAdapter { position ->
            deleteVehicle(position)
        }

        with(binding.vehicleList) {
            adapter = vehicleAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        binding.listEmptyTextView.isVisible = vehicles.isEmpty()
    }

    private fun deleteVehicle(position: Int) {
        vehicles = vehicles.filterIndexed { index, _ ->
            index != position
        }
        with(vehicleAdapter) {
            updateVehicle(vehicles)
            notifyItemRemoved(position)
        }
        
        binding.listEmptyTextView.isVisible = vehicles.isEmpty()
    }

    private fun createVehicle(nameModel:String, nameMake: String, isElectric:Boolean): Vehicle{
       return if (isElectric) {
            Vehicle.ElectricCar(
                modelName = nameModel,
                makeCar = nameMake,
                avatarLink = LINK_ELECTRIC,
                typeCar = "Электромобиль"
            )
        } else {
            Vehicle.Car(
                modelName = nameModel,
                makeCar = nameMake,
                avatarLink = LINK_CAR
            )
        }
    }

    private fun addVehicleToList() {
        Log.d("TAG", "vehicles.size = ${vehicles.size}")
        val newVehicle = createVehicle(textModel, textMake, isElectric)
        vehicles = listOf(newVehicle) + vehicles
        with(vehicleAdapter) {
            updateVehicle(vehicles)
            notifyItemInserted(0)
        }
        binding.vehicleList.scrollToPosition(0)

        binding.listEmptyTextView.isVisible = vehicles.isEmpty()
        Log.d("TAG", "vehicles.size = ${vehicles.size}")
    }

//    private fun testForEmptinessVehicles(vehicles: List<Vehicle>){
//        binding.listEmptyTextView.isVisible = vehicles.isNotEmpty()
//    }

    override fun onTransferDate(inputModel: String, inputMake: String, type: Boolean) {
        textModel = inputModel
        textMake = inputMake
        isElectric = type
        addVehicleToList()

        binding.listEmptyTextView.isVisible = vehicles.isEmpty()

    }

    companion object {
        const val LINK_ELECTRIC =
            "https://i0.wp.com/itc.ua/wp-content/uploads/2020/01/mercedes-benz-vision-avtr-3.jpg"
        const val LINK_CAR =
            "https://img1.fonwall.ru/o/ei/cars-toyota-crossover-red.jpeg?route=thumb&h=350"

        const val KEY_VEHICLES = "key_vehicles"
    }
}