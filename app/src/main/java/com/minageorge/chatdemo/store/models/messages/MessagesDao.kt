package com.minageorge.chatdemo.store.models.messages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(messages: MessagesEntity)

    @Query("select * from messages_table WHERE room_id=:roomId ORDER BY date DESC")
    fun getMessages(roomId: Long): Flowable<List<MessagesEntity>>
}