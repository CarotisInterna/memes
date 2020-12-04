package ru.popova.memes.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.popova.memes.dto.AuthInfo
import ru.popova.memes.dto.LoginRequestDto
import ru.popova.memes.dto.LogoutResponseDto

interface AuthApi {
    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequestDto): Call<AuthInfo?>

    @POST("auth/logout")
    fun logout(): Call<LogoutResponseDto?>
}