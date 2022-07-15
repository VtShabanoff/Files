package com.skillbox.messageapp.data.db.entities

object UserContract {
    const val TABLE_NAME = "users"

    object Columns {
        const val ID = "user_id"
        const val FIRST_NAME = "user_first_name"
        const val LAST_NAME = "user_last_name"
        const val AVATAR = "user_avatar"
    }
}