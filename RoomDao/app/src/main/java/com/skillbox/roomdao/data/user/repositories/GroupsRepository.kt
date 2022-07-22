package com.skillbox.roomdao.data.user.repositories

import android.app.Application

class GroupsRepository(context: Application) {

    companion object {
        private var INSTANCE: GroupsRepository? = null
        fun initialize(context: Application) {
            if (INSTANCE == null) INSTANCE = GroupsRepository(context)
        }

        fun getInstance(): GroupsRepository {
            return INSTANCE ?: throw IllegalStateException("GroupsRepository must be initialized")
        }
    }

}