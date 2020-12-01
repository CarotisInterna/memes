package ru.popova.memes.task

import ru.popova.memes.dto.LoginRequestDto
import ru.popova.memes.util.TaskResult

class ProxyLoginTask : LoginTask() {
    override fun doInBackground(vararg params: LoginRequestDto): TaskResult<Any?, Exception> {
        //login with test user creds
        return super.doInBackground(
            LoginRequestDto(
                "qwerty",
                "qwerty"
            )
        )
    }
}