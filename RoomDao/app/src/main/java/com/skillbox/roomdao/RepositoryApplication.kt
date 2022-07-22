package com.skillbox.roomdao

import android.app.Application
import com.skillbox.roomdao.data.user.repositories.ContactsGroupsRepository
import com.skillbox.roomdao.data.user.repositories.GroupsRepository
import com.skillbox.roomdao.data.user.repositories.UserRepository

class RepositoryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ContactsGroupsRepository.initialize(this)
        UserRepository.initialize(this)
        GroupsRepository.initialize(this)
    }
}