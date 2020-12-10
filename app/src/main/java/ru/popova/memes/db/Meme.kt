package ru.popova.memes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.popova.memes.dto.MemeDto

@Entity
data class Meme(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "created") val createdDate: Long,
    @ColumnInfo(name = "photo_url") val photoUrl: String
) {
    constructor(dto: MemeDto) : this(
        dto.id,
        dto.title,
        dto.description,
        dto.isFavorite,
        dto.createdDate,
        dto.photoUrl
    )
}