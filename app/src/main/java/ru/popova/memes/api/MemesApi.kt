package ru.popova.memes.api

import retrofit2.http.GET

interface MemesApi {
    @GET("memes")
    fun memes(): Map<Any, Any>
}

