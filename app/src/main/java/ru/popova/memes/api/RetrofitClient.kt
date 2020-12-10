package ru.popova.memes.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var client: Retrofit? = null

    fun getClient(url: String): Retrofit {
        if (client == null) {
            client = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return client!!
    }
}