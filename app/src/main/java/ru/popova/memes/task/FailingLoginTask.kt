package ru.popova.memes.task

import ru.popova.memes.dto.LoginRequestDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.TaskResult

class FailingLoginTask : LoginTask() {
    override fun doInBackground(vararg params: LoginRequestDto): TaskResult<Any?, Exception> {
        return Failure(RuntimeException())
    }
}