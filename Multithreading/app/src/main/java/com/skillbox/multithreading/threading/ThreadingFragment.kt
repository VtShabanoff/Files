package com.skillbox.multithreading.threading

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.AdapterMovie
import com.skillbox.multithreading.AutoClearedValue
import com.skillbox.multithreading.MovieViewModel
import com.skillbox.multithreading.R
import com.skillbox.multithreading.databinding.FragmentThreadingBinding

class ThreadingFragment : Fragment(R.layout.fragment_threading) {
    private val binding by viewBinding(FragmentThreadingBinding::bind)
    private val movieViewModel: MovieViewModel by viewModels()
    private var movieAdapter by AutoClearedValue<AdapterMovie>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.requestMovie.setOnClickListener {
            movieViewModel.requestMovie()
        }
        movieViewModel.movies
            .observe(viewLifecycleOwner){ newMovies ->
                movieAdapter.updateMovie(newMovies)
            }
        initRecyclerView()
    }
    private fun initRecyclerView(){
        movieAdapter = AdapterMovie()
        with(binding.movieRecyclerView){
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
}