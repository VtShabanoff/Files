package com.skillbox.github.ui.current_user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentCurrentUserBinding
import kotlinx.coroutines.launch

class CurrentUserFragment : Fragment(R.layout.fragment_current_user) {
    private val binding by viewBinding(FragmentCurrentUserBinding::class.java)
    private val viewModel: CurrentUserViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.remoteUser.observe(viewLifecycleOwner) { remoteUser ->
            Glide.with(view)
                .load(remoteUser.avatar)
                .into(binding.avatarIV)
            binding.nameTV.text = remoteUser.name
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            binding.nameTV.text = errorMessage
        }
        lifecycleScope.launch {
            viewModel.getUser()
        }
    }
}