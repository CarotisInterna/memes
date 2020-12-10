package ru.popova.memes.dto

import java.io.Serializable

data class ErrorDto(
    val code: Int,
    val errorMessage: String
) : Serializable