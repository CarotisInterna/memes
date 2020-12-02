package ru.popova.memes.task

import ru.popova.memes.dto.ErrorDto
import ru.popova.memes.dto.MemeDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.TaskResult

class FailingMemeTask : MemeTask() {
    override fun doInBackground(vararg params: Void?): TaskResult<List<MemeDto>, ErrorDto> {
        return Failure(ErrorDto(500, "error"))
    }
}