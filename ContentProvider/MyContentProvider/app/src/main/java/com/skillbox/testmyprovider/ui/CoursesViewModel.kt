package com.skillbox.testmyprovider.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.testmyprovider.data.RepositoryContentImpl
import com.skillbox.testmyprovider.domain.Course
import kotlinx.coroutines.launch

class CoursesViewModel(context: Application) : AndroidViewModel(context) {
    private val repo = RepositoryContentImpl(context)

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> = _courses

    private val _isDeleteCourse = MutableLiveData<Int>()
    val isDeleteCourse: LiveData<Int> = _isDeleteCourse

    fun getAllCourses(){
        viewModelScope.launch {
           _courses.postValue(repo.getAllCourses())
        }
    }

    fun getCourseById(id: Long){
        viewModelScope.launch {
            repo.getCourseById(id)
        }
    }

    fun deleteAllCourses(){
        viewModelScope.launch {
            _isDeleteCourse.postValue(repo.deleteAllCourses())
        }
    }

    fun deleteCourseById(id: Long){
        viewModelScope.launch {
            _isDeleteCourse.postValue(repo.deleteCourseById(id))
        }
    }
}