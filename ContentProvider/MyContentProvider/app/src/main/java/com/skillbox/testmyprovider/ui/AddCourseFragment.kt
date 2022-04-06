package com.skillbox.testmyprovider.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.testmyprovider.R
import com.skillbox.testmyprovider.databinding.FragmentAddBinding

class AddCourseFragment: Fragment(R.layout.fragment_add) {
    private val binding by viewBinding(FragmentAddBinding::class.java)
    private val viewModel: AddCourseViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTextChangeListeners()
        addCourse()
        observeViewModel()
    }

    private fun addCourse(){
        binding.saveCourseBtn.setOnClickListener {
            viewModel.addCourse(
                binding.etTitle.text.toString()
            )
        }
    }

    private fun observeViewModel(){
        viewModel.errorInputTitle.observe(viewLifecycleOwner) {
            val message = if (it) {
                "Не корректно"

            } else {
                null
            }
            binding.tilTitle.error = message
            Log.d("errorInputName", "error live data = $it")
        }

        viewModel.closeScreen.observe(viewLifecycleOwner) {
            viewModel.closeScreen.observe(viewLifecycleOwner) {
                activity?.onBackPressed()
            }
        }
    }

    private fun addTextChangeListeners(){
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputTitle()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}