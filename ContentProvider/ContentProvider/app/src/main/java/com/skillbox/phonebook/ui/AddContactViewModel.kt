package com.skillbox.phonebook.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.phonebook.data.RepositoryImpl
import com.skillbox.phonebook.domain.Contact
import kotlinx.coroutines.launch

class AddContactViewModel(context: Application) : AndroidViewModel(context) {
    private val repo = RepositoryImpl(context)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> = _errorInputName

    private val _errorInputPhoneNumber = MutableLiveData<Boolean>()
    val errorInputPhoneNumber: LiveData<Boolean> = _errorInputPhoneNumber

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit> = _closeScreen

    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> = _contact

    fun addContact(
        inputName: String?,
        inputFamilyName: String?,
        inputPhoneNumber: String?,
        inputEmail: String?
    ) {
        val name = parseName(inputName)
        val familyName = parseFamilyName(inputFamilyName)
        val phoneNumber = parsePhoneNumber(inputPhoneNumber)
        val email = parseEmail(inputEmail)
        val validArgs = validateInfo(name,phoneNumber)
        Log.d("errorInputName", "validArgs = $validArgs")
        if (validArgs) {
            viewModelScope.launch {
                repo.saveContact(name, familyName, phoneNumber, email)
            }
            finishAdd()
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseFamilyName(inputFamilyName: String?): String {
        return inputFamilyName?.trim() ?: ""
    }

    private fun parsePhoneNumber(inputPhoneNumber: String?): String {
        return inputPhoneNumber?.trim() ?: ""
    }

    private fun parseEmail(inputEmail: String?): String {
        return inputEmail?.trim() ?: ""
    }

    private fun validateInfo(name: String, phoneNumber: String): Boolean {
        var result = true

        if (name.isBlank()) {
            _errorInputName.postValue(true)
           result = false

        }

        if (phoneNumber.isBlank()) {
            _errorInputPhoneNumber.postValue(true)
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.postValue(false)
    }

    fun resetErrorInputPhoneNumber() {
        _errorInputPhoneNumber.postValue(false)
    }
    private fun finishAdd(){
        _closeScreen.postValue(Unit)
    }
}