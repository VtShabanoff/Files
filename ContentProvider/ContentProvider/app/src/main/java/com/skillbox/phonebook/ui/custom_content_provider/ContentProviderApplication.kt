package com.skillbox.phonebook.ui.custom_content_provider

import android.app.Application
import android.os.StrictMode
import com.skillbox.phonebook.BuildConfig

class ContentProviderApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyDeath()
                    .build()
            )
        }
    }
}