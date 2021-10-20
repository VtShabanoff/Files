package com.skillbox.networking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.networking.databinding.ItemListMovieBinding

class AdapterMovieList: ListAdapter<Movie, AdapterMovieList.MovieHolder>(MovieDiffUtilCallback()) {

    class MovieDiffUtilCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class MovieHolder(
        binding: ItemListMovieBinding
    ): RecyclerView.ViewHolder(binding.root){

        private val idTVH = binding.textViewId
        private val titleTVH = binding.textViewTitle
        private val yearTVH = binding.textViewYear
        private val typeTVH = binding.textViewType
        private val posterIMH = binding.posterIV

        fun bind(movie: Movie){
            idTVH.text = movie.id
            titleTVH.text = movie.title
            yearTVH.text = movie.year
            typeTVH.text = movie.type
            Glide.with(itemView)
                .load(movie.poster)
                .error(R.drawable.icon_poster_error)
                .into(posterIMH)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ItemListMovieBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    fun updateMovie(newItem: List<Movie>){
        submitList(newItem)
    }
}

