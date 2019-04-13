package com.minageorge.chatdemo.store.models.subscribers

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.minageorge.chatdemo.store.models.rooms.RoomsEntity

@Entity(
    tableName = "subscribers_table",
    indices = [Index(value = ["room_id"])],
    foreignKeys = [ForeignKey(
        entity = RoomsEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("room_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SubscribersEntity(
    @PrimaryKey
    val id: Long,
    val room_id: Long,
    val subscriber_name: String,
    val subscriber_image: String
) {
    constructor() : this(0, 0, "", "")
}