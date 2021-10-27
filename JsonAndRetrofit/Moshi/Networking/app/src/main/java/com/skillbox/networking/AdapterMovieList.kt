package com.skillbox.networking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.networking.databinding.ItemListMovieBinding

class AdapterMovieList(
    private val onItemClick: (id: String) -> Unit
): ListAdapter<Movie, AdapterMovieList.MovieHolder>(MovieDiffUtilCallback()) {

    class MovieDiffUtilCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class MovieHolder(
        binding: ItemListMovieBinding,
        onItemClick: (id: String) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        private val titleTVH = binding.textViewTitle
        private val yearTVH = binding.textViewYear
        private val genreTVH = binding.textViewGenre
        private val ratedTVH = binding.textViewRating
        private val typeTVH = binding.textViewType
        private val posterIMH = binding.posterIV
        private val ratingsTVH = binding.textViewAppraisals
        private lateinit var id: String

        init {
            binding.root.setOnClickListener { onItemClick(id) }
        }

        fun bind(movie: Movie){

            id = movie.id
            titleTVH.text = movie.title
            yearTVH.text = movie.year
            genreTVH.text = movie.genre
            ratedTVH.text = movie.rated.toString()
            typeTVH.text = movie.type
            ratingsTVH.text = movie.ratings.map{"${it.key} = ${it.value}"}.joinToString(
                separator = "\n"
            )

            Glide.with(itemView)
                .load(movie.poster)
                .error(R.drawable.icon_poster_error)
                .into(posterIMH)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ItemListMovieBinding.inflate(LayoutInflater.from(parent.context),
            parent, false), onItemClick)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    fun updateMovie(newItem: List<Movie>){
        submitList(newItem)
    }
}

