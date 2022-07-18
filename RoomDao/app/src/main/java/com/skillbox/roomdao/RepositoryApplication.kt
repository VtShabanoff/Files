package com.skillbox.roomdao

import android.app.Application
import com.skillbox.roomdao.data.user.repositories.ContactsRepository
import com.skillbox.roomdao.data.user.repositories.UserRepository

class RepositoryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ContactsRepository.initialize(this)
        UserRepository.initialize(this)
    }
}