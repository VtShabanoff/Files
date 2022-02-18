package com.vt_shabanoff.files.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.vt_shabanoff.files.R
import com.vt_shabanoff.files.databinding.FragmentFirstBinding
import java.io.File

class FirstFragment : Fragment(R.layout.fragment_first) {
    private val binding by viewBinding(FragmentFirstBinding::class.java)
    private lateinit var viewModel: ViewModelFirstFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModelFirstFragment::class.java]

        binding.downloadBtn.setOnClickListener {

            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.downloadFile(binding.inputTextEt.text.toString(), nameFile())
        setLoader()
        showToastDownloadFile()
        showToastIsFileDownloaded()
    }

    private fun showToastIsFileDownloaded() {
        viewModel.downloadedFile.observe(viewLifecycleOwner) { isDownLoaded ->
            when(isDownLoaded){
                true -> toast(requireContext(), nameFile())
                false -> {}
            }
        }
    }

    private fun showToastDownloadFile() {
        viewModel.downloadState.observe(viewLifecycleOwner) {
            if (it) toast(requireContext(), "file downloaded")
            else toast(requireContext(), "error download")
        }
    }

    private fun nameFile(): String {
        val timestamp = System.currentTimeMillis()
        val nameFile = File(TEST_URL).name
        return String.format("${timestamp}_${nameFile}")
    }

    private fun setLoader() {
        viewModel.isLoadingFile.observe(viewLifecycleOwner) { isLoading ->
            when (isLoading) {
                true -> {
                    binding.progressBar.isVisible = true
                    binding.downloadBtn.isVisible = false
                    binding.inputTextEt.isVisible = false
                }
                false -> {
                    binding.progressBar.isVisible = false
                    binding.downloadBtn.isVisible = true
                    binding.inputTextEt.isVisible = true
                }
            }
        }
    }


    companion object {
        const val TEST_URL =
            "https://github.com/VtShabanoff/Composition/blob/master/app/src/main/AndroidManifest.xml"
    }
}