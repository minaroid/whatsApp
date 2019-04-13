package com.minageorge.chatdemo.store

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.minageorge.chatdemo.store.models.messages.MessagesEntity
import com.minageorge.chatdemo.store.models.messages.MessagesDao
import com.minageorge.chatdemo.store.models.rooms.RoomsEntity
import com.minageorge.chatdemo.store.models.subscribers.SubscribersDao
import com.minageorge.chatdemo.store.models.subscribers.SubscribersEntity
import com.minageorge.uber.store.model.markermodel.RoomsDao


@Database(entities = [RoomsEntity::class, SubscribersEntity::class,
    MessagesEntity::class], version = 18, exportSchema = false)
@TypeConverters(DateTypeConverters::class)
abstract class ChatDataBase : RoomDatabase() {

    abstract fun getRoomsDao(): RoomsDao

    abstract fun getSubscribersDao(): SubscribersDao

    abstract fun getMessagesDao(): MessagesDao

    companion object {
        @Volatile
        private var instance: ChatDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ChatDataBase::class.java, "chat.db"
            ).build()
    }
}