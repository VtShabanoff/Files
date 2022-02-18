package com.vt_shabanoff.files.ui

import android.app.Application
import android.os.StrictMode
import com.vt_shabanoff.files.BuildConfig

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyDeath()
                .build()
        }
    }
}