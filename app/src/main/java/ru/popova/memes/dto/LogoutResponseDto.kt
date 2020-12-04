package ru.popova.memes.dto

import com.google.gson.annotations.SerializedName

data class LogoutResponseDto(
    val code: Int,
    @SerializedName("мessage")
    val message: String
)
