package ru.popova.memes.task

import android.util.Log
import ru.popova.memes.MemeModel
import ru.popova.memes.MemesApplication
import ru.popova.memes.dto.ErrorDto
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import ru.popova.memes.util.TaskResult

open class MemeFromDbLoadingTask : MemeLoadingTask() {
    override fun doInBackground(vararg params: Void?): TaskResult<List<MemeModel>, ErrorDto> {
        return try {
            val response = MemesApplication.db.memeDao().getAll()
            Success(response.map { MemeModel(it) })
        } catch (e: Exception) {
            Log.e(
                MemeFromDbLoadingTask::class.toString(),
                "error: $e"
            )
            Failure(ErrorDto(500, e.toString()))
        }
    }
}