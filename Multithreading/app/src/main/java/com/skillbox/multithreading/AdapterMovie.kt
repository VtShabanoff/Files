package com.skillbox.multithreading

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.multithreading.databinding.ItemMovieBinding
import com.skillbox.multithreading.networking.Movie

class AdapterMovie(): ListAdapter<Movie, AdapterMovie.MovieHolder>(MovieDiffUtilCallback()) {

    class MovieDiffUtilCallback: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    class MovieHolder(
        binding: ItemMovieBinding
    ): RecyclerView.ViewHolder(binding.root){

        private val titleTVH = binding.titleTV
        private val yearTVH = binding.yearTV

        fun bind(movie: Movie){
            titleTVH.text = movie.title
            yearTVH.text = movie.year.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateMovie(newItem: List<Movie>){
        submitList(newItem)
    }

}