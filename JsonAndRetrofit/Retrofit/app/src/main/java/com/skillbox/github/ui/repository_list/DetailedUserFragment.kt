package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentDetailedUserBinding

class DetailedUserFragment : Fragment(R.layout.fragment_detailed_user) {
    private val binding by viewBinding(FragmentDetailedUserBinding::class.java)
    private val viewModel: DetailedInfoUserViewModel by viewModels()
    private val args by navArgs<DetailedUserFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModelDetailedIfo()
        bindViewModelIsStarred()
    }

    private fun bindViewModelDetailedIfo() {
        viewModel.detailedInfo.observe(viewLifecycleOwner) { remoteRepository ->
            binding.textView.text = remoteRepository.owner.nameOwner
            Glide.with(binding.root)
                .load(remoteRepository.owner.avatar)
                .into(binding.avatarIV)

        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            isError(message)
        }
        viewModel.getDetailedInfo(args.nameOwner, args.nameRepo)
    }

    private fun bindViewModelIsStarred(){
        viewModel.isStarred.observe(viewLifecycleOwner) { isStarred ->
            binding.starYellowIV.isVisible = isStarred
            binding.starEmptyIV.isVisible = !isStarred
        }
        viewModel.errorMessageIsStarred.observe(viewLifecycleOwner) { message ->
            isError(message)
        }
        viewModel.isStarred(args.nameOwner, args.nameRepo)
    }

    private fun isError(message: String) {
        binding.avatarIV.isVisible = false
        binding.starEmptyIV.isVisible = false
        binding.starYellowIV.isVisible = false
        binding.textView.text = message
    }
}