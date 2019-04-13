package com.minageorge.chatdemo.store.models.rooms

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "rooms_table")
data class RoomsEntity(
    @PrimaryKey
    val id: Long,
    val room_name: String,
    val last_message: String,
    val room_image: String,
    val last_date: Date?,
    val new_counter: Int
) {
    constructor() : this( 0, "", "", "", null, 0)
}