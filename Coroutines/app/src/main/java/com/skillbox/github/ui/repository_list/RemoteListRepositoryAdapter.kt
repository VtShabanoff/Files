package com.skillbox.github.ui.repository_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.github.R
import com.skillbox.github.data.RemoteRepository

class RemoteListRepositoryAdapter(
    private val onItemClick: (nameRepo: String, nameOwner: String) -> Unit
) : ListAdapter<RemoteRepository, RemoteListRepositoryAdapter.HolderRemoteRepo>(
    RemoteRepoListDiffUtilCallback()
) {

    class RemoteRepoListDiffUtilCallback() : DiffUtil.ItemCallback<RemoteRepository>() {
        override fun areItemsTheSame(oldItem: RemoteRepository, newItem: RemoteRepository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RemoteRepository, newItem: RemoteRepository): Boolean {
            return oldItem == newItem
        }
    }

    class HolderRemoteRepo(
        onItemClick: (nameRepo: String, nameOwner: String) -> Unit,
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private lateinit var nameOwner: String
        private lateinit var repo: String
        private var nodeId: TextView = view.findViewById(R.id.nodeId)
        private val nameRepo: TextView = view.findViewById(R.id.nameRepo)

        init {
            view.setOnClickListener {
                onItemClick(nameOwner, repo)
            }
        }

        fun bind(remoteRepository: RemoteRepository) {
            nodeId.text = remoteRepository.nodeId
            nameRepo.text = remoteRepository.name
            nameOwner = remoteRepository.owner.nameOwner
            repo = remoteRepository.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRemoteRepo {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_repository,
            parent,
            false
        )
        return HolderRemoteRepo(onItemClick, view)
    }

    override fun onBindViewHolder(holder: HolderRemoteRepo, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateList(newList: List<RemoteRepository>){
        submitList(newList)
    }
}