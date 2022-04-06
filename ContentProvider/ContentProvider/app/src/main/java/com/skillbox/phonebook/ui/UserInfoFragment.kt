package com.skillbox.phonebook.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.phonebook.R
import com.skillbox.phonebook.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment(R.layout.fragment_user_info) {
    private val binding by viewBinding(FragmentUserInfoBinding::class.java)
    private val viewModel: UserInfoViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = requireArguments().getString(ContactsFragment.NAME)
        val idArgs = requireArguments().getLong(AddContactFragment.CONTACT_ITEM_ID)
        val permissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.WRITE_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {
            observeViewModel()
            getArgsUserInfo(name, idArgs)
            deleteContact(idArgs)
        } else {
            Log.d("UserInfoFragment", "error permission -> UserInfoFragment")
        }
    }
    private fun observeViewModel(){
        viewModel.phoneNumbers.observe(viewLifecycleOwner){
            binding.phoneNumbersTV.text = it.joinToString("\n")
        }
        viewModel.emails.observe(viewLifecycleOwner){
            binding.emailYV.text = it.joinToString("\n")
        }
        viewModel.contact.observe(viewLifecycleOwner){

        }
    }
    private fun getArgsUserInfo(name: String?, id: Long) {
        val inputName = parseName(name)
        binding.nameTV.text = inputName
        viewModel.savePhones(id)
        viewModel.saveEmails(id)
    }

    private fun parseName(name: String?): String{
        return name?.trim() ?: ""
    }

    private fun deleteContact(id: Long){
        binding.deleteContactBtn.setOnClickListener {
            viewModel.deleteContact(id)

            viewModel.contact.observe(viewLifecycleOwner){
                if (it == 1){
                    findNavController()
                        .navigate(R.id.action_userInfoFragment_to_contactsFragment)
                    Log.d("deleteContact", "delete -> $it")
                }else{
                    Log.d("deleteContact", "delete -> $it")
                }
            }
        }
    }

}