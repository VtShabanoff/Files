package com.skillbox.car.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.skillbox.car.AutoClearedValue
import com.skillbox.car.`interface`.TransferringDate
import com.skillbox.car.adapter.VehicleAdapter
import com.skillbox.car.data_class.Vehicle
import com.skillbox.car.databinding.FragmentLayoutGridStaggeredBinding
import com.skillbox.car.dialog.DialogCreateVehicle
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import java.lang.Exception
import kotlin.random.Random

class StaggeredGridLayoutFragment: Fragment(), TransferringDate {
    private var vehicleAdapter by AutoClearedValue<VehicleAdapter>()
    private var _binding: FragmentLayoutGridStaggeredBinding? = null
    private val binding: FragmentLayoutGridStaggeredBinding
        get() = _binding!!

    private lateinit var textModel: String
    private lateinit var textMake: String
    private var isElectric = false

    private var vehicles:List<Vehicle> = arrayListOf(
        Vehicle.Car(
            id = Random.nextLong(),
            "Mazda",
            "6",
            LINK_CAR
        ),
        Vehicle.ElectricCar(
            id = Random.nextLong(),
            "Nissan",
            "leaf",
            LINK_ELECTRIC,
            "electric"
        ),
        Vehicle.Car(id = Random.nextLong(),
            "Mazda",
            "6",
            LINK_CAR
        ),
        Vehicle.ElectricCar(
            id = Random.nextLong(),
            "Nissan",
            "leaf",
            LINK_ELECTRIC,
            "electric"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLayoutGridStaggeredBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null){
            vehicles = savedInstanceState.getParcelableArrayList(KEY_GRID_STAGGERED)
                ?: throw Exception("vehicles = null")
            setTextListIsEmpty(vehicles)
        }

        binding.addFAD.setOnClickListener {
            DialogCreateVehicle().show(childFragmentManager, "TAG")
        }
        initRecycleView()

        vehicleAdapter.items = vehicles
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(KEY_GRID_STAGGERED, vehicles as ArrayList)
    }
    private fun initRecycleView() {
        vehicleAdapter = VehicleAdapter { position ->
            deleteVehicle(position)
        }

        with(binding.vehicleList) {
            adapter = vehicleAdapter

            val dividerItemDecorationVertical = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            val dividerItemDecorationHorizontal = DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
            addItemDecoration(dividerItemDecorationVertical)
            addItemDecoration(dividerItemDecorationHorizontal)

            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
        }
        setTextListIsEmpty(vehicles)
    }
    private fun setTextListIsEmpty(vehicles: List<Vehicle>){
        binding.listEmptyTextView.isVisible = vehicles.isEmpty()
    }
    private fun deleteVehicle(position: Int){
        vehicles = vehicles.filterIndexed { index, _ ->
            index != position
        }
        with(vehicleAdapter) {
            items = vehicles
        }
        setTextListIsEmpty(vehicles)
    }
    private fun createListVehicle(model: String, make: String, isElectric: Boolean): Vehicle {
        return if (isElectric){
            Vehicle.ElectricCar(
                id = Random.nextLong(),
                modelName = model,
                makeCar = make,
                avatarLink = LINK_ELECTRIC,
                typeCar = "electric")
        }else{
            Vehicle.Car(
                id = Random.nextLong(),
                modelName = model,
                makeCar = make,
                avatarLink = LINK_CAR
            )
        }
    }

    private fun addVehicleToList(){
        val newVehicle = createListVehicle(textModel, textMake, isElectric)
        vehicles = listOf(newVehicle) + vehicles

        with(vehicleAdapter){
            items = vehicles
        }

        binding.vehicleList.scrollToPosition(0)
        setTextListIsEmpty(vehicles)
    }

    override fun onTransferDate(inputModel: String, inputMake: String, type: Boolean) {
        textModel = inputModel
        textMake = inputMake
        isElectric = type
        addVehicleToList()
        setTextListIsEmpty(vehicles)
    }

    companion object {
        const val LINK_ELECTRIC =
            "https://i0.wp.com/itc.ua/wp-content/uploads/2020/01/mercedes-benz-vision-avtr-3.jpg"
        const val LINK_CAR =
            "https://img1.fonwall.ru/o/ei/cars-toyota-crossover-red.jpeg?route=thumb&h=350"

        const val KEY_GRID_STAGGERED = "key_linear_grid_staggered"
    }
}