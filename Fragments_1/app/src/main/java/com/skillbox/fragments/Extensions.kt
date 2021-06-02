package com.skillbox.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T: Fragment> T.withArguments(action: Bundle.() -> Unit): T{
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}