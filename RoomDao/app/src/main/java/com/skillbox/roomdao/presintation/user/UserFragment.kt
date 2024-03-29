package com.skillbox.roomdao.presintation.user

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.skillbox.roomdao.R
import com.skillbox.roomdao.data.entities.EEmail
import com.skillbox.roomdao.data.entities.EUser

class UserFragment : Fragment(R.layout.fragment_user) {
    private val viewModel by viewModels<ViewModerUser>()
    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var btn: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.tvUserName)
        email = view.findViewById(R.id.tvEmailUser)
        btn = view.findViewById(R.id.btnContacts)
        addUserWithEmail()
        initData()
        navigateToContacts()
    }

    private fun initData() {
        viewModel.usersWithEmail.observe(viewLifecycleOwner) {
            it.map { relation ->
                name.text = relation.eUser.name
                email.text = relation.eEmail.email
            }
        }
    }

    private fun navigateToContacts() {
        btn.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_contactsFragment)
        }
    }

    private fun addUserWithEmail(){
        viewModel.addUser(EUser(1, "Vitaly"))
        viewModel.addEmail(EEmail(1, "vitaly@mail.ru"))
    }

}