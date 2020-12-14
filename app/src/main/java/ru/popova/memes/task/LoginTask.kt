package ru.popova.memes.task

import android.os.AsyncTask
import android.util.Log
import ru.popova.memes.api.Api
import ru.popova.memes.dto.AuthInfo
import ru.popova.memes.dto.ErrorDto
import ru.popova.memes.dto.LoginRequestDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import ru.popova.memes.util.TaskResult

open class LoginTask : AsyncTask<LoginRequestDto, Void, TaskResult<AuthInfo, ErrorDto?>>() {
    override fun doInBackground(vararg params: LoginRequestDto): TaskResult<AuthInfo, ErrorDto?> {
        return try {
            val login = Api.authApi
                .login(params[0]).execute()
            if (login.isSuccessful && login.body() != null) {
                val value = login.body()
                Log.i(
                    LoginTask::class.toString(),
                    "result: $value"
                )
                Success(value!!)
            } else {
                val errorBody = login.errorBody() as ErrorDto?
                Log.e(
                    LoginTask::class.toString(),
                    "error: $errorBody"
                )
                Failure(errorBody)
            }
        } catch (e: Exception) {
            Failure(ErrorDto(500, e.toString()))
        }
    }
}