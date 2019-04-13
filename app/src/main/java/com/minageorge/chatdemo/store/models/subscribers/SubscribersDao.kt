package com.minageorge.chatdemo.store.models.subscribers

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface SubscribersDao {

    @Query("select * from subscribers_table WHERE room_id=:roomId")
    fun getSubscribers(roomId: Long): Flowable<List<SubscribersEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(subscriber: SubscribersEntity)
}