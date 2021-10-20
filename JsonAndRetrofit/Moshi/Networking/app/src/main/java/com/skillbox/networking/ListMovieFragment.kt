package com.skillbox.networking

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.networking.databinding.FragmentListMovieBinding

class ListMovieFragment: Fragment(R.layout.fragment_list_movie) {
    private val binding by viewBinding(FragmentListMovieBinding::class.java)
    private var adapterMovie by AutoClearedValue<AdapterMovieList>()
    private val viewModel: ViewModelMovieList by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieTypesMenu()
        initRecyclerViewListMovie()
        bindViewModel()
        setMessageError()

    }

    private fun setMovieTypesMenu(){
        val movieTypes = resources.getStringArray(R.array.movie_types)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, movieTypes)
        binding.autoCompleteTV.setAdapter(adapter)
    }

    private fun initRecyclerViewListMovie(){
        adapterMovie = AdapterMovieList()
        with(binding.recyclerViewListMovie){
            adapter = adapterMovie
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel(){
        var selectedType = ""

        binding.autoCompleteTV.setOnItemClickListener { adapterView, _, i,_ ->
            selectedType = adapterView.getItemAtPosition(i).toString()
        }

        binding.buttonSearch.setOnClickListener {
            val queryText = binding.editTextSearchByName.text.toString()
            val queryYear = binding.editTextSearchByYear.text.toString()
            viewModel.search(queryText, queryYear, selectedType)
        }

        viewModel.isLoadingListMovie.observe(viewLifecycleOwner, ::updateIsLoadingMovies)
        viewModel.movies.observe(viewLifecycleOwner){adapterMovie.updateMovie(it)}

    }

    private fun setMessageError(){
        var selectedType = ""

        binding.autoCompleteTV.setOnItemClickListener { adapterView, _, i,_ ->
            selectedType = adapterView.getItemAtPosition(i).toString()
        }
        binding.buttonRequest.setOnClickListener {
            binding.errorTV.isVisible = false
            val queryText = binding.editTextSearchByName.text.toString()
            val queryYear = binding.editTextSearchByYear.text.toString()
            viewModel.search(queryText, queryYear, selectedType)
        }
        viewModel.massageError.observe(viewLifecycleOwner){messageError ->
            binding.errorTV.text = messageError
        }
        viewModel.isMessage.observe(viewLifecycleOwner){ isError ->
            binding.errorTV.isVisible = isError
        }
    }

    private fun updateIsLoadingMovies(isLoading: Boolean){
        binding.recyclerViewListMovie.isVisible = isLoading.not()
        binding.buttonSearch.isEnabled = isLoading.not()
        binding.progressBar.isVisible = isLoading
    }


}