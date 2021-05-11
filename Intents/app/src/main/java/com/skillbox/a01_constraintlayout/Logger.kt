package com.skillbox.a01_constraintlayout

import android.util.Log

class Logger {

companion object{
    const val TAG = "Main"
}

    fun verbose(message: String){
        Log.v(TAG, message)
    }

    fun debug(message: String){
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message)
        }
    }

    fun info(message: String){
        if (BuildConfig.DEBUG){
            Log.i(TAG, message)
        }
    }

    fun error(message: String){
        if (BuildConfig.DEBUG) {
            Log.e(TAG, message)
        }
    }
    fun assert(message: String){
        if (BuildConfig.DEBUG) {
            Log.println(Log.ASSERT, TAG, message)
        }
    }

}