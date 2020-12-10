package ru.popova.memes.task

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import ru.popova.memes.api.Api
import ru.popova.memes.dto.ErrorDto
import ru.popova.memes.dto.LogoutResponseDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import ru.popova.memes.util.TaskResult

class LogoutTask : AsyncTask<Void, Void, TaskResult<LogoutResponseDto?, ErrorDto>>() {
    override fun doInBackground(vararg params: Void?): TaskResult<LogoutResponseDto?, ErrorDto> {
        return try {
            val response = Api.authApi.logout().execute()
            if (response.isSuccessful && response.body() != null) {
                val value = response.body()
                Success(value)
            } else {
                val error =
                    Gson().fromJson(response.errorBody().toString(), ErrorDto::class.java)
                Log.e(
                    LogoutTask::class.toString(),
                    "error: $error"
                )
                Failure(error)
            }
        } catch (e: Exception) {
            Failure(ErrorDto(500, e.toString()))
        }
    }
}