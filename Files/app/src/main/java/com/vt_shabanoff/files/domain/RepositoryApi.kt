package com.vt_shabanoff.files.domain

import android.content.SharedPreferences

interface RepositoryApi {
    suspend fun downloadFile(
        url: String,
        fileName: String
    )

    suspend fun saveInfoAboutFile(
        url: String,
        fileName: String
    )
    suspend fun saveFirstBoot()

    suspend fun  getSharedPref(save: String):SharedPreferences
    suspend fun downloadFilesFirstTime()
}