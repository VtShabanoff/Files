package com.skillbox.github.ui.current_user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentCurrentUserBinding

class CurrentUserFragment : Fragment(R.layout.fragment_current_user) {
    private val binding by viewBinding(FragmentCurrentUserBinding::class.java)
    private val viewModel: CurrentUserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.remoteUser.observe(requireActivity()) {
            it.avatar = binding.avatarIV.toString()
            it.name = binding.nameTV.toString()
        }
    }
}