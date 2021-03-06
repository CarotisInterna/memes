package ru.popova.memes

import ru.popova.memes.db.Meme
import ru.popova.memes.dto.MemeDto
import java.io.Serializable

data class MemeModel(
    val id: String,
    val title: String,
    val description: String,
    var isFavorite: Boolean,
    val createdDate: Long,
    val photoUrl: String,
    val local: Boolean
) : Serializable {
    constructor(dto: MemeDto) : this(
        dto.id,
        dto.title,
        dto.description,
        dto.isFavorite,
        dto.createdDate,
        dto.photoUrl,
        false
    )

    constructor(entity: Meme) : this(
        entity.id,
        entity.title,
        entity.description,
        entity.isFavorite,
        entity.createdDate,
        entity.photoUrl,
        entity.isLocal
    )
}