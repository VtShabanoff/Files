package com.skillbox.testmyprovider.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.testmyprovider.data.RepositoryContentImpl
import com.skillbox.testmyprovider.domain.Course
import kotlinx.coroutines.launch

class AddCourseViewModel(private val app: Application) : AndroidViewModel(app) {

    private val repo = RepositoryContentImpl(app)

    private val _errorInputTitle = MutableLiveData<Boolean>()
    val errorInputTitle: LiveData<Boolean> = _errorInputTitle

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit> = _closeScreen

    private val _course = MutableLiveData<Course>()
    val contact: LiveData<Course> = _course

    fun addCourse(
        inputTitle: String?
    ) {
        val title = parseTitle(inputTitle)
        val validTitle = validateInfo(title)
        if (validTitle) {
            viewModelScope.launch {
                repo.addCourse(title)
            }
            finishAdd()
        }
    }

    private fun parseTitle(inputTitle: String?): String {
        return inputTitle?.trim() ?: ""
    }

    private fun validateInfo(title: String): Boolean {
        var result = true

        if (title.isBlank()) {
            _errorInputTitle.postValue(true)
            result = false

        }
        return result
    }

    fun resetErrorInputTitle() {
        _errorInputTitle.postValue(false)
    }

    private fun finishAdd() {
        _closeScreen.postValue(Unit)
    }
}