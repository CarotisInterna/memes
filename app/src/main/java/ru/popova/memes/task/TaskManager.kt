package ru.popova.memes.task

object TaskManager {
    val loginTask: LoginTask
        get() = ProxyLoginTask()
    val memeTask: MemeTask
        get() = MemeTask()
}