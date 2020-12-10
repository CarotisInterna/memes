package ru.popova.memes.api

import retrofit2.Call
import retrofit2.http.GET
import ru.popova.memes.dto.MemeDto

interface MemesApi {
    @GET("memes")
    fun memes(): Call<List<MemeDto>>
}

