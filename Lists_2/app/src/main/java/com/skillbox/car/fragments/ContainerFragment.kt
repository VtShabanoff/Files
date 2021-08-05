package com.skillbox.car.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skillbox.car.R
import com.skillbox.car.`interface`.ShowGridLayout
import com.skillbox.car.`interface`.ShowLinerLayoutFragment
import com.skillbox.car.`interface`.ShowListVehicles
import com.skillbox.car.`interface`.ShowStaggeredGridLayout
import com.skillbox.car.databinding.FragmentContainerBinding

class ContainerFragment: Fragment(), ShowListVehicles, ShowLinerLayoutFragment,
ShowGridLayout, ShowStaggeredGridLayout{
    private var _binding: FragmentContainerBinding? = null
    private val binding: FragmentContainerBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showNavigationFragment()
    }

    private fun showNavigationFragment() {
        childFragmentManager.beginTransaction()
        .replace(R.id.containerFragment, NavigationFragment())
        .commit()
    }

    override fun onClickListenerOnButton() {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.containerFragment, VehicleListFragment())
            .commit()
    }

    override fun onCalledLinerLayout() {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.containerFragment, LinerLayoutListFragment())
            .commit()
    }

    override fun onCalledGridLayout() {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.containerFragment, GridLayoutFragment())
            .commit()
    }

    override fun onCalledStaggeredGridLayout() {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.containerFragment, StaggeredGridLayoutFragment())
            .commit()
    }
}