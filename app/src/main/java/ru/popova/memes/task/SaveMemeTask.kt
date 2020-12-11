package ru.popova.memes.task

import android.os.AsyncTask
import ru.popova.memes.MemeModel
import ru.popova.memes.MemesApplication
import ru.popova.memes.db.Meme
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import ru.popova.memes.util.TaskResult

class SaveMemeTask : AsyncTask<MemeModel, Void, TaskResult<Void?, Void?>>() {
    override fun doInBackground(vararg params: MemeModel): TaskResult<Void?, Void?> {
        return try {
            MemesApplication.db.memeDao().insertOrUpdate(params.asList().map {
                Meme(it)
            })
            Success(null)
        } catch (e: Exception) {
            Failure(null)
        }
    }
}