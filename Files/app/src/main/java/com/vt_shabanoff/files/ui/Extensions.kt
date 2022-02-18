package com.vt_shabanoff.files.ui

import android.content.Context
import android.view.Gravity
import android.widget.Toast

val toast = fun(context: Context, message: String){
    val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.show()
}