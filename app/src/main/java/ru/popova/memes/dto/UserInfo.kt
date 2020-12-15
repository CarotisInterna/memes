package ru.popova.memes.dto

import java.io.Serializable

data class UserInfo(
    val id: Int,
    val username: String?,
    val firstName: String?,
    val lastName: String?,
    val userDescription: String?
) : Serializable