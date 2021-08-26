package com.skillbox.permissionsanddate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skillbox.permissionsanddate.`interface`.OnFragmentLocationCallInterface
import com.skillbox.permissionsanddate.databinding.FragmentContainerBinding

class ContainerFragment : Fragment(), OnFragmentLocationCallInterface {
    private var _binding: FragmentContainerBinding? = null
    private val binding: FragmentContainerBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showPermitLocationFragment()
    }

    private fun showPermitLocationFragment() {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, LocationFragment())
            .commit()
    }

    override fun onCallFragmentLocation() {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, LocationFragment())
            .commit()
    }

}