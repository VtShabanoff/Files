package com.skillbox.phonebook.domain

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.skillbox.phonebook.R
import kotlin.random.Random

data class Contact(
    val name: String,
    val phoneNumber: String,
    @DrawableRes val iconPhone: Int = R.drawable.ic_phone,
    val id: Long = Random.nextLong()
)
