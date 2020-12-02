package ru.popova.memes.task

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import ru.popova.memes.api.Api
import ru.popova.memes.dto.ErrorDto
import ru.popova.memes.dto.MemeDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import ru.popova.memes.util.TaskResult

open class MemeTask : AsyncTask<Void, Void, TaskResult<List<MemeDto>, ErrorDto>>() {
    override fun doInBackground(vararg params: Void?): TaskResult<List<MemeDto>, ErrorDto> {
        return try {
            val response = Api.memesApi.memes().execute()
            if (response.isSuccessful && response.body() != null) {
                val value = response.body() as List<MemeDto>
                Log.i(MemeTask::class.toString(), "Memes: $value")
                Success(value)
            } else {
                val error =
                    Gson().fromJson(response.errorBody().toString(), ErrorDto::class.java)
                Log.e(
                    MemeTask::class.toString(),
                    "error: $error"
                )
                Failure(error)
            }
        } catch (e: Exception) {
            Failure(ErrorDto(500, e.toString()))
        }
    }
}