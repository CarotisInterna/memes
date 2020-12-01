package ru.popova.memes.util

sealed class TaskResult<out Success, out Failure>

data class Success<out Success>(val value: Success) : TaskResult<Success, Nothing>()
data class Failure<out Failure>(val reason: Failure) : TaskResult<Nothing, Failure>()