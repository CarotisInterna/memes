package ru.popova.memes.api

object Api {
    private const val url = "https://r2.mocker.surfstudio.ru/android_vsu/"

    val memesApi: MemesApi = RetrofitClient.getClient(url).create(MemesApi::class.java)

    val authApi: AuthApi = RetrofitClient.getClient(url).create(AuthApi::class.java)
}