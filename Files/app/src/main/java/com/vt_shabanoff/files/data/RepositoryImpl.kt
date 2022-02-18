package com.vt_shabanoff.files.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.vt_shabanoff.files.domain.RepositoryApi
import java.io.File
import java.io.IOException
import kotlin.contracts.contract

class RepositoryImpl(private val application: Application): RepositoryApi {

    override suspend fun downloadFile(url: String, fileName: String) {
        val folder = application.getExternalFilesDir("my_folder")
        val file = File(folder, fileName)
        try {
                file.outputStream().use { outputStream ->
                    Networking.api.getFile(url).byteStream()
                        .use { inputStream ->
                            inputStream.copyTo(outputStream)
                        }
            }
                saveInfoAboutFile(url, fileName)
        }catch (t: IOException){
            Log.d("errorFile", "errorFile = $t")
        }

        file.exists()
        file.length()
        Log.d("exists", "file.exists() = ${file.exists()}, file.length() = ${file.length()}")

    }
    override suspend fun saveInfoAboutFile(url: String, fileName: String) {
       getSharedPref(SHARED_PREF).apply {
            edit()
                .putString(url, fileName)
                .apply()
       }
    }

    override suspend fun saveFirstBoot() {
        getSharedPref(SHARED_PREF_2).apply {
            edit()
                .putBoolean(KEY_SHARED_PREF_FIRST_TIME_DOWNLOAD, false)
                .apply()
        }
    }

    override suspend fun getSharedPref(save: String): SharedPreferences {
        return application
            .getSharedPreferences(save, Context.MODE_PRIVATE)
    }

    override suspend fun downloadFilesFirstTime() {
        try {
            val firstTimeDownload = getSharedPref(SHARED_PREF_2)
                .getBoolean(KEY_SHARED_PREF_FIRST_TIME_DOWNLOAD, true)

            if (firstTimeDownload){
                application.resources.assets.open("FileUrls")
                    .bufferedReader()
                    .use {
                        it.readLines()
                    }
                saveFirstBoot()
            }
        }catch (t: Throwable){
            t.message
        }

    }


    companion object{
    const val SHARED_PREF = "name_shared_pref"
    const val SHARED_PREF_2 = "name_shared_pref_2"
    const val KEY_SHARED_PREF_FIRST_TIME_DOWNLOAD = "key_shared_first_time_download"
}
}