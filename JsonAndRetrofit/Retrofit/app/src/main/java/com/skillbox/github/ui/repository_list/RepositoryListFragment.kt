package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentListRepositoryBinding

class RepositoryListFragment : Fragment(R.layout.fragment_list_repository) {

    private val binding by viewBinding(FragmentListRepositoryBinding::class.java)
    private val viewModel: RepositoryListViewModel by viewModels()
    private val adapterRepo
        get() = requireNotNull(binding.repoListRV.adapter as RemoteListRepositoryAdapter) {
            error("RemoteListRepositoryAdapter not init")
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        with(binding.repoListRV) {
            adapter = RemoteListRepositoryAdapter { nameRepo, nameOwner ->
                val detailedInfo =
                    RepositoryListFragmentDirections.actionRepositoryListFragmentToDetailedUserFragment(
                        nameRepo,
                        nameOwner
                    )
                findNavController().navigate(detailedInfo)

            }
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun initViewModel() {
        viewModel.repositories.observe(viewLifecycleOwner) { repositories ->
            adapterRepo.updateList(repositories)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.errorTV.isVisible = true
            binding.repoListRV.isVisible = false
            binding.errorTV.text = it
        }
        viewModel.getRepositories()
    }

}