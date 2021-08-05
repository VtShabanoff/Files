package com.skillbox.car.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skillbox.car.`interface`.ShowGridLayout
import com.skillbox.car.`interface`.ShowLinerLayoutFragment
import com.skillbox.car.`interface`.ShowListVehicles
import com.skillbox.car.`interface`.ShowStaggeredGridLayout
import com.skillbox.car.databinding.FragmentNavigationBinding

class NavigationFragment: Fragment(){
    private var _binding: FragmentNavigationBinding? = null
    private val binding: FragmentNavigationBinding
        get() = _binding!!

    private val showListVehicles: ShowListVehicles?
        get() = parentFragment as ShowListVehicles?

    private val showLinerLayoutFragment: ShowLinerLayoutFragment?
        get() = parentFragment as ShowLinerLayoutFragment?

    private val showGridLayoutFragment: ShowGridLayout?
        get() = parentFragment as ShowGridLayout?

    private val showStaggeredGridLayout: ShowStaggeredGridLayout?
        get() = parentFragment as ShowStaggeredGridLayout?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vehicleListButton.setOnClickListener {
            showListVehicleFragment()
        }
        binding.buttonLinerLayout.setOnClickListener {
            showLinearLayoutFragment()
        }
        binding.buttonGridLayout.setOnClickListener {
            showGridLayout()
        }
        binding.buttonStaggeredGrid.setOnClickListener {
            showStaggeredGridLayout()
        }
    }

    private fun showListVehicleFragment() {
        showListVehicles?.onClickListenerOnButton()
    }

    private fun showLinearLayoutFragment(){
        showLinerLayoutFragment?.onCalledLinerLayout()
    }

    private fun showGridLayout(){
        showGridLayoutFragment?.onCalledGridLayout()
    }
    private fun showStaggeredGridLayout(){
        showStaggeredGridLayout?.onCalledStaggeredGridLayout()
    }


}