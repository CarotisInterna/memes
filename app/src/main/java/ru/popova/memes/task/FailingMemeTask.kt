package ru.popova.memes.task

import ru.popova.memes.MemeModel
import ru.popova.memes.dto.ErrorDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.TaskResult

class FailingMemeTask : MemeLoadingTask() {
    override fun doInBackground(vararg params: Void?): TaskResult<List<MemeModel>, ErrorDto> {
        return Failure(ErrorDto(500, "error"))
    }
}