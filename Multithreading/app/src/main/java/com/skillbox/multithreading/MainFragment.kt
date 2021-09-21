package com.skillbox.multithreading

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.threading.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_threadingFragment)
        }
        binding.deadlock.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_deadlockFragment)
        }
        binding.raceCondition.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_raceConditionFragment)
        }
        binding.livelock.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_livelockFragment)
        }
    }
}