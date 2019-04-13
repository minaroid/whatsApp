package com.minageorge.uber.store.model.markermodel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minageorge.chatdemo.store.models.rooms.RoomsEntity
import io.reactivex.Flowable

@Dao
interface RoomsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(room: RoomsEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(rooms: List<RoomsEntity>)

    @Query("select * from rooms_table ORDER BY last_date DESC")
    fun getRooms(): Flowable<List<RoomsEntity>>
}