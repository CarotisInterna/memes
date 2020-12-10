package ru.popova.memes.task

import android.util.Log
import com.google.gson.Gson
import ru.popova.memes.MemeModel
import ru.popova.memes.MemesApplication
import ru.popova.memes.api.Api
import ru.popova.memes.db.Meme
import ru.popova.memes.dto.ErrorDto
import ru.popova.memes.dto.MemeDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import ru.popova.memes.util.TaskResult

open class MemeFromApiLoadingTask : MemeLoadingTask() {
    override fun doInBackground(vararg params: Void?): TaskResult<List<MemeModel>, ErrorDto> {
        return try {
            val response = Api.memesApi.memes().execute()
            if (response.isSuccessful && response.body() != null) {
                val value = response.body() as List<MemeDto>
                Log.i(MemeFromApiLoadingTask::class.toString(), "Memes: $value")

                MemesApplication.db.memeDao().insertOrUpdate(value.map{ Meme(it) })

                Success(value.map { MemeModel(it) })
            } else {
                val error =
                    Gson().fromJson(response.errorBody().toString(), ErrorDto::class.java)
                Log.e(
                    MemeFromApiLoadingTask::class.toString(),
                    "error: $error"
                )
                Failure(error)
            }
        } catch (e: Exception) {
            Failure(ErrorDto(500, e.toString()))
        }
    }
}