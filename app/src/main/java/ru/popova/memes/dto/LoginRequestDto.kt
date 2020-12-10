package ru.popova.memes.dto

import java.io.Serializable

data class LoginRequestDto(
    val login: String,
    val password: String
) : Serializable