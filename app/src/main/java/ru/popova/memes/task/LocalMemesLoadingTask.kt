package ru.popova.memes.task

import android.os.AsyncTask
import ru.popova.memes.MemeModel
import ru.popova.memes.MemesApplication
import ru.popova.memes.dto.ErrorDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import ru.popova.memes.util.TaskResult

class LocalMemesLoadingTask :
    AsyncTask<Void, Void, TaskResult<List<MemeModel>, ErrorDto>>() {
    override fun doInBackground(vararg params: Void?): TaskResult<List<MemeModel>, ErrorDto> {
        return try {
            val result = MemesApplication.db.memeDao().getLocal().map { MemeModel(it) }
            Success(result)
        } catch (e: Exception) {
            Failure(ErrorDto(500, e.toString()))
        }
    }
}