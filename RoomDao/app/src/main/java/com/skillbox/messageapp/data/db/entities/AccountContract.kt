package com.skillbox.messageapp.data.db.entities

object AccountContract {
    const val TABLE_NAME = "accounts"
    object Columns {
        const val ID = "account_id"
        const val USER_ID = "account_user_id"
        const val EMAIL = "account_email"
    }
}