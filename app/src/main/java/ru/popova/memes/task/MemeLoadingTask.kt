package ru.popova.memes.task

import android.os.AsyncTask
import ru.popova.memes.MemeModel
import ru.popova.memes.dto.ErrorDto
import ru.popova.memes.util.TaskResult

abstract class MemeLoadingTask : AsyncTask<Void, Void, TaskResult<List<MemeModel>, ErrorDto>>()