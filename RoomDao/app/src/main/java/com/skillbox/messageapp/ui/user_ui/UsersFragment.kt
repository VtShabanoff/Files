package com.skillbox.messageapp.ui.user_ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.messageapp.R
import com.skillbox.messageapp.databinding.FragmentUsersBinding
import com.skillbox.messageapp.domain.models.Account
import com.skillbox.messageapp.domain.models.User

class UsersFragment : Fragment(R.layout.fragment_users) {
    private val binding by viewBinding(FragmentUsersBinding::class.java)
    private val viewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        viewModel.addUser(
            User(
                id = 1,
                "Petya",
                "Ivanov",
                "avatar"
            )
        )
        viewModel.addAccount(
            Account(
                id = 1,
                "mail@mail.com"
            )
        )
        binding.buttonAddUser.setOnClickListener {
            viewModel.getUsersAndAccounts()
        }

        viewModel.usersAndAccounts.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)
        }
    }

    private fun initRV(){
        adapter = UserListAdapter{
            findNavController()
                .navigate(R.id.action_usersFragment_to_contactsFragment)
        }
        binding.usersRv.adapter = adapter
        binding.usersRv.setHasFixedSize(true)
    }

}