package ru.popova.memes.dto

import java.io.Serializable

data class MemeDto(
    val id: String,
    val title: String,
    val description: String,
    val isFavorite: Boolean,
    val createdDate: Long,
    val photoUrl: String
) : Serializable