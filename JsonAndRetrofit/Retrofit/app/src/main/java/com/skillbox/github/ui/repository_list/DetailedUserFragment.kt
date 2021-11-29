package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.data.RemoteRepository
import com.skillbox.github.databinding.FragmentDetailedUserBinding
import kotlin.properties.Delegates

class DetailedUserFragment : Fragment(R.layout.fragment_detailed_user) {
    private val binding by viewBinding(FragmentDetailedUserBinding::class.java)
    private val viewModel: DetailedInfoUserViewModel by viewModels()
    private val args by navArgs<DetailedUserFragmentArgs>()
    private var isStarredF = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getDetailedInfo()
        observeViewModelState()
        bindStar()
        Log.d("isStarredF", "isStarredF = $isStarredF")
    }

    private fun getDetailedInfo() {
        viewModel.isStarred(args.nameOwner, args.nameRepo)
        viewModel.getDetailedInfo(args.nameOwner, args.nameRepo)

    }

    private fun isError(message: String) {
        binding.avatarIV.isVisible = false
        binding.starIV.isVisible = false
        binding.textView.text = message
    }

    private fun bindRepo(remoteRepository: RemoteRepository) {
        binding.textView.text = remoteRepository.owner.nameOwner
        Glide.with(binding.root)
            .load(remoteRepository.owner.avatar)
            .into(binding.avatarIV)
    }

    private fun bindStar(){
        if (isStarredF) {
            binding.starIV.setImageResource(R.drawable.ic_yellow_star)
        } else {
            binding.starIV.setImageResource(R.drawable.ic_gray_star)
        }

        binding.starIV.setOnClickListener {
            if (isStarredF) {
                setYellowStar(args.nameOwner, args.nameRepo)
            } else {
                setGrayStar(args.nameOwner, args.nameRepo)
            }
        }
    }

    private fun observeViewModelState() {
        viewModel.isStarred.observe(viewLifecycleOwner) { isStarred ->

            isStarredF = isStarred
        }
        viewModel.detailedInfo.observe(viewLifecycleOwner) { remoteRepo ->
            bindRepo(remoteRepo)
        }
        viewModel.errorMessageIsStarred.observe(viewLifecycleOwner) { message ->
            isError(message)
        }
        viewModel.setStarred.observe(viewLifecycleOwner) { setStarred ->
            if (setStarred) binding.starIV.setImageResource(R.drawable.ic_gray_star)
        }
        viewModel.deleteStarred.observe(viewLifecycleOwner) { delete ->
            if (delete) binding.starIV.setImageResource(R.drawable.ic_yellow_star)
        }
    }

    private fun setYellowStar(nameOwner: String, nameRepo: String){
        binding.starIV.setImageResource(R.drawable.ic_yellow_star)
        viewModel.setStarred(nameOwner, nameRepo)
    }
    private fun setGrayStar(nameOwner: String, nameRepo: String){
        binding.starIV.setImageResource(R.drawable.ic_gray_star)
        viewModel.deleteStarred(nameOwner, nameRepo)
    }
}