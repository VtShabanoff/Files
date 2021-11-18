package com.skillbox.github.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.data.RemoteUser

class RemoteUserAdapter: ListAdapter<RemoteUser, RemoteUserAdapter.HolderRUA>(RemoteUserListDiffUtilCallback()) {

    class RemoteUserListDiffUtilCallback(): DiffUtil.ItemCallback<RemoteUser>(){
        override fun areItemsTheSame(oldItem: RemoteUser, newItem: RemoteUser): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RemoteUser, newItem: RemoteUser): Boolean {
            return oldItem == newItem
        }
    }

    class HolderRUA(view: View): RecyclerView.ViewHolder(view){
        var avatar: ImageView = view.findViewById(R.id.avatarIV)
        private val nameUser: TextView = view.findViewById(R.id.nameUserTV)

        fun bind (user: RemoteUser){
            nameUser.text = user.name
            Glide.with(itemView)
                .load(user.avatar)
                .into(avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRUA {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_remote_user,
            parent,
            false
        )
        return HolderRUA(view)
    }

    override fun onBindViewHolder(holder: HolderRUA, position: Int) {
        holder.bind(currentList[position])
    }
}