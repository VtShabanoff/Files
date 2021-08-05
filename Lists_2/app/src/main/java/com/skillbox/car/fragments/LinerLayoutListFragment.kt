package com.skillbox.car.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.car.AutoClearedValue
import com.skillbox.car.`interface`.TransferringDate
import com.skillbox.car.adapter.VehicleAdapter
import com.skillbox.car.data_class.Vehicle
import com.skillbox.car.databinding.FragmentListLayoutLinerVehicleBinding
import com.skillbox.car.dialog.DialogCreateVehicle
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import java.lang.Exception
import kotlin.random.Random

class LinerLayoutListFragment: Fragment(), TransferringDate {
    private var vehicleAdapter by AutoClearedValue<VehicleAdapter>()
    private var _binding: FragmentListLayoutLinerVehicleBinding? = null
    private val binding: FragmentListLayoutLinerVehicleBinding
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
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListLayoutLinerVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null){
            vehicles = savedInstanceState.getParcelableArrayList(KEY_LINEAR_LAYOUT)
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

        outState.putParcelableArrayList(KEY_LINEAR_LAYOUT, vehicles as ArrayList)
    }
    private fun initRecycleView() {
        vehicleAdapter = VehicleAdapter { position ->
            deleteVehicle(position)
        }

        with(binding.vehicleList) {
            adapter = vehicleAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val layout = layoutManager as LinearLayoutManager
                    val itemCount = layout.itemCount
                    val lastVisibleItemPosition = layout.findLastVisibleItemPosition()

                    if (
                        lastVisibleItemPosition == itemCount - 1 && itemCount < MAX_LIST_SIZE
                    ){
                        vehicles = vehicles + listOf(
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
                            )
                        )
                        with(vehicleAdapter){
                            items = vehicles
                        }
                    }
                }
            })
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
    private fun createListVehicle(model: String, make: String, isElectric: Boolean): Vehicle{
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

        const val KEY_LINEAR_LAYOUT = "key_linear_layout"

        const val MAX_LIST_SIZE = 10
    }
}