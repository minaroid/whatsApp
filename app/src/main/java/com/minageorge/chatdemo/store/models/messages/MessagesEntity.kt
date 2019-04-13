package com.minageorge.chatdemo.store.models.messages

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.minageorge.chatdemo.store.models.rooms.RoomsEntity
import java.util.*

@Entity(
    tableName = "messages_table",
    indices = [Index(value = ["room_id"])],
    foreignKeys = [ForeignKey(
        entity = RoomsEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("room_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class MessagesEntity(
    @PrimaryKey
    val id: Long,
    val room_id: Long,
    val user_id: Long,
    val message: String,
    val date: Date?
) {
    constructor() : this(0, 0, 0, "", null)
}