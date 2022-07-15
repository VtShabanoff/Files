package com.skillbox.messageapp.ui.group_ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.messageapp.R
import com.skillbox.messageapp.databinding.FragmentGroupsContactsBinding

class ContactsGroupsFragment: Fragment(R.layout.fragment_groups_contacts) {
    private val binding by viewBinding(FragmentGroupsContactsBinding::class.java)
    private val viewModel: GroupViewModel by viewModels()


}