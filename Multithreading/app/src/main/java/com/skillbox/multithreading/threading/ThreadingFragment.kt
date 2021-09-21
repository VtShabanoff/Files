package com.skillbox.multithreading.threading

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.MovieViewModel
import com.skillbox.multithreading.R
import com.skillbox.multithreading.databinding.FragmentThreadingBinding

class ThreadingFragment : Fragment(R.layout.fragment_threading) {
    private val binding by viewBinding(FragmentThreadingBinding::bind)
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.requestMovie.setOnClickListener {
            movieViewModel.requestMovie()
        }
    }

}