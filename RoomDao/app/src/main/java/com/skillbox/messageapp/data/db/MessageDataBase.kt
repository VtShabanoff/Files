package com.skillbox.messageapp.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skillbox.messageapp.data.db.dao.ContactDao
import com.skillbox.messageapp.data.db.dao.ContactGroupDao
import com.skillbox.messageapp.data.db.dao.GroupDao
import com.skillbox.messageapp.data.db.dao.UserDao
import com.skillbox.messageapp.data.db.entities.EntityAccount
import com.skillbox.messageapp.data.db.entities.EntityContact
import com.skillbox.messageapp.data.db.entities.EntityUser
import com.skillbox.messageapp.data.db.entities.groups_to_contact.GroupEntity

@Database(
    entities = [EntityUser::class, EntityAccount::class, EntityContact::class, GroupEntity::class],
    version = MessageDataBase.VERSION_DB,
    exportSchema = false
)
abstract class MessageDataBase: RoomDatabase() {

    companion object {
        const val VERSION_DB = 1
         var INSTANCE: MessageDataBase? = null
        private const val DB_NAME = "message.db"
        private val LOCK = Any()

        fun getInstance(application: Application): MessageDataBase{
            INSTANCE?.let { messageDataBase ->
                return messageDataBase
            }

            synchronized(LOCK){
                INSTANCE?.let { messageDataBase ->
                    return messageDataBase
                }

                val db = Room.databaseBuilder(
                    application,
                    MessageDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db

                return db
            }
        }
    }

    abstract fun contactGroupCrossRefDao(): ContactGroupDao
    abstract fun groupDao(): GroupDao
    abstract fun userDao(): UserDao
    abstract fun contactDao(): ContactDao
}