package com.skillbox.phonebook.domain

import androidx.annotation.DrawableRes
import com.skillbox.phonebook.R

data class Contact(
    val name: String,
    val phoneNumbers: List<String>,
    @DrawableRes val iconPhone: Int = R.drawable.ic_phone,
    val emails: List<String>,
    val id: Long
)
