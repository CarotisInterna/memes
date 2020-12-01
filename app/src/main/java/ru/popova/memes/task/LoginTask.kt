package ru.popova.memes.task

import android.os.AsyncTask
import android.util.Log
import ru.popova.memes.api.Api
import ru.popova.memes.dto.AuthInfo
import ru.popova.memes.dto.LoginRequestDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import ru.popova.memes.util.TaskResult

open class LoginTask : AsyncTask<LoginRequestDto, Void, TaskResult<AuthInfo, Exception>>() {
    override fun doInBackground(vararg params: LoginRequestDto): TaskResult<AuthInfo, Exception> {
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
                Log.e(
                    LoginTask::class.toString(),
                    "error: ${login.errorBody()}"
                )
                throw RuntimeException()
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }
}