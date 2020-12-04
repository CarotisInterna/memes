package ru.popova.memes.task

import android.os.AsyncTask
import ru.popova.memes.MemesApplication

class CheckDbIsEmptyTask : AsyncTask<Void, Void, Boolean>() {
    override fun doInBackground(vararg params: Void?): Boolean =
        MemesApplication.db.memeDao().count() == 0
}