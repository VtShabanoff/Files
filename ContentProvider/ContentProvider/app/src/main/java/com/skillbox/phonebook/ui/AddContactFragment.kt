package com.skillbox.phonebook.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.phonebook.R
import com.skillbox.phonebook.databinding.FragmentAddContactBinding

class AddContactFragment : Fragment(R.layout.fragment_add_contact) {
    private val binding by viewBinding(FragmentAddContactBinding::class.java)
    private val viewModel: AddContactViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val permissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.WRITE_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {
            addTextChangeListeners()
            addContact()
            observeViewModel()
        } else {
            requestPermission()
        }
    }

    private fun addContact() {
        binding.saveButton.setOnClickListener {
            viewModel
                .addContact(
                    binding.etName.text?.toString(),
                    binding.etFamilyName.text?.toString(),
                    binding.etPhoneNumber.text?.toString(),
                    binding.etEmail.text?.toString()
                )
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                "Не корректно"

            } else {
                null
            }
            binding.tilName.error = message
            Log.d("errorInputName", "error live data = $it")
        }

        viewModel.errorInputPhoneNumber.observe(viewLifecycleOwner) {
            val message = if (it) {
                "Не корректно"
            } else {
                null
            }
            binding.tilPhoneNumber.error = message
        }

        viewModel.closeScreen.observe(viewLifecycleOwner) {
            viewModel.closeScreen.observe(viewLifecycleOwner) {
                activity?.onBackPressed()
            }
        }
    }

    private fun addTextChangeListeners() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()

                Log.d("onTextChanged", "viewModel.resetErrorInputName() = ${viewModel.resetErrorInputName()}")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputPhoneNumber()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                android.Manifest.permission.WRITE_CONTACTS
            ),
            WRITE_CONTACTS_RC
        )
    }

    companion object {
        const val CONTACT_ITEM_ID = "contact_item_id"
        const val WRITE_CONTACTS_RC = 200
    }
}