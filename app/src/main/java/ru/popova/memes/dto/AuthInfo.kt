package ru.popova.memes.dto

import java.io.Serializable

data class AuthInfo(
    val accessToken: String,
    val userInfo: UserInfo
) : Serializable