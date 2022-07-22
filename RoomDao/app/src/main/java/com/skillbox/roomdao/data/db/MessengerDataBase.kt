package com.skillbox.roomdao.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skillbox.roomdao.data.dao.ContactDao
import com.skillbox.roomdao.data.dao.ContactsGroupsCrossRefDao
import com.skillbox.roomdao.data.dao.DaoUser
import com.skillbox.roomdao.data.dao.GroupDao
import com.skillbox.roomdao.data.entities.*

@Database(
    entities = [
        EUser::class,
        EEmail::class,
        EContact::class,
        EGroup::class,
        ContactGroupCrossRef::class],
    version = MessengerDataBase.VERSION_DB,
    exportSchema = false
)
abstract class MessengerDataBase : RoomDatabase() {

    companion object {
        const val VERSION_DB = 1
        private var INSTANCE: MessengerDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "db_messenger"

        fun getInstance(context: Context): MessengerDataBase {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
            }

            val db = Room.databaseBuilder(
                context.applicationContext,
                MessengerDataBase::class.java,
                DB_NAME
            ).build()

            INSTANCE = db

            return db
        }

    }

    abstract fun getUserDao(): DaoUser
    abstract fun getContactDao(): ContactDao
    abstract fun getGroupDao(): GroupDao
    abstract fun getContactsGroupsCrossRef(): ContactsGroupsCrossRefDao
}