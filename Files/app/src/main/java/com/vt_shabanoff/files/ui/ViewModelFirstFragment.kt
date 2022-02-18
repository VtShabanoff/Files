package com.vt_shabanoff.files.ui

import android.app.Application
import android.os.Environment
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vt_shabanoff.files.data.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelFirstFragment(private val app: Application) : AndroidViewModel(app) {
    private val repository = RepositoryImpl(app)

    private val _isLoadingFile = MutableLiveData<Boolean>()
    val isLoadingFile: LiveData<Boolean>
        get() = _isLoadingFile

    private val _downloadState = SingleLiveEvent<Boolean>()
    val downloadState: LiveData<Boolean>
        get() = _downloadState

    private val _isDownloadedFile = SingleLiveEvent<Boolean>()
    val downloadedFile: LiveData<Boolean>
        get() = _isDownloadedFile


    fun downloadFile(url: String, fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED){
                return@launch
            }
            val isLoadedFile = repository.getSharedPref(SHARED_PREF).contains(url)
            Log.d("isLoadedFile", "isLoadedFile = $isLoadedFile")

            _isDownloadedFile.postValue(false)

            try {
                when(isLoadedFile){
                    true -> _isDownloadedFile.postValue(true)
                    false -> {
                        _isDownloadedFile.postValue(false)
                        repository.downloadFilesFirstTime()
                        repository.downloadFile(url, fileName)
                        _isLoadingFile.postValue(true)
                    }
                }

                _downloadState.postValue(true)

            }catch (t: Throwable){
                _downloadState.postValue(false)
                val dir = app.getExternalFilesDir(null)
                dir?.deleteRecursively()
            }finally {
                _isLoadingFile.postValue(false)
            }

        }
    }
    companion object{
        const val SHARED_PREF = "name_shared_pref"
    }
}