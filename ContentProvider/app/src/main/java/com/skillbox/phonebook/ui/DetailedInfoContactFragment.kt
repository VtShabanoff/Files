package com.skillbox.phonebook.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.phonebook.R
import com.skillbox.phonebook.databinding.FragmentDetailedContactInfoBinding

class DetailedInfoContactFragment: Fragment(R.layout.fragment_detailed_contact_info) {
    private val binding by viewBinding(FragmentDetailedContactInfoBinding::class.java)
    private val viewModel: DetailedInfoContactViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeViewModel(){
        viewModel.errorInputName.observe(viewLifecycleOwner){
            val message = if (it){
                "Не корректно"
            }else{
                null
            }
            binding.tilName.error = message
        }
        viewModel.errorInputPhoneNumber.observe(viewLifecycleOwner){
            val message = if (it){
                "Не корректно"
            }else{
                null
            }
            binding.tilPhoneNumber.error = message
        }
    }

    private fun addTextChangeListeners() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}