package com.skillbox.phonebook.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.phonebook.data.RepositoryImpl
import com.skillbox.phonebook.domain.Contact

class DetailedInfoContactViewModel: ViewModel() {
    private val repo = RepositoryImpl

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> = _errorInputName

    private val _errorInputPhoneNumber = MutableLiveData<Boolean>()
    val errorInputPhoneNumber: LiveData<Boolean> = _errorInputPhoneNumber

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit> = _closeScreen

    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> = _contact

    fun addContact(inputName: String?, inputPhoneNumber: String?){
        val name = parseName(inputName)
        val phoneNumber = parsePhoneNumber(inputPhoneNumber)
        val validArgs = validateInfo(name, phoneNumber)
        if (validArgs){
            val contact = Contact(name, phoneNumber)
            repo.addContact(contact)
            finishScreen()
        }
    }

    fun editContact(inputName: String?, inputPhoneNumber: String?){
        val name = parseName(inputName)
        val phoneNumber = parsePhoneNumber(inputPhoneNumber)
        val validArgs = validateInfo(name, phoneNumber)
        if (validArgs){
            _contact.value?.let {
                val item = it.copy(name = name, phoneNumber = phoneNumber)
                repo.editContact(item)
                finishScreen()
            }
        }

    }

    private fun parseName(inputName: String?): String{
        return inputName?.trim() ?: ""
    }

    private fun parsePhoneNumber(inputPhoneNumber: String?): String{
        return inputPhoneNumber?.trim() ?: ""
    }

    private fun validateInfo(name: String, phoneNumber: String): Boolean{
        val result = true

        if (name.isBlank()){
            _errorInputName.postValue(true)
            return false
        }

        if (phoneNumber.isBlank()){
            _errorInputPhoneNumber.postValue(true)
            return false
        }

        return result
    }

    private fun finishScreen(){
        _closeScreen.postValue(Unit)
    }

    fun resetErrorInputName() {
        _errorInputName.postValue(false)
    }

    fun resetErrorInputCount() {
        _errorInputPhoneNumber.postValue(false)
    }
}