package com.skillbox.messageapp.ui.user_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.messageapp.databinding.ItemUserBinding
import com.skillbox.messageapp.domain.models.UserAndAccount

class UserListAdapter(
    private val onclickItem: (id: Long) -> Unit
) : ListAdapter<UserAndAccount, UserListAdapter.UserHolder>(
    DiffUtilUser()
) {

    class DiffUtilUser : DiffUtil.ItemCallback<UserAndAccount>() {
        override fun areItemsTheSame(oldItem: UserAndAccount, newItem: UserAndAccount): Boolean {
            return oldItem.user.id == newItem.user.id
        }

        override fun areContentsTheSame(oldItem: UserAndAccount, newItem: UserAndAccount): Boolean {
            return oldItem == newItem
        }
    }

    class UserHolder(private val binding: ItemUserBinding, onclickItem: (id: Long) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var id = 0L
        init {
            binding.root.setOnClickListener {
                onclickItem(id)
            }
        }
        fun bind(userAndAccount: UserAndAccount) {
            binding.firstNameTv.text = userAndAccount.user.firstName
            binding.lastNameTv.text = userAndAccount.user.lastName
            binding.emailTv.text = userAndAccount.account.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onclickItem
        )
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}