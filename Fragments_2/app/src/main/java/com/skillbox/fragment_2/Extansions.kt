package com.skillbox.fragment_2

import android.app.Fragment
import android.os.Bundle

fun <T: Fragment> T.withArguments(action: Bundle.() -> Unit): T{
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}