package ru.popova.memes

import android.app.Application
import androidx.room.Room
import ru.popova.memes.db.AppDatabase

class MemesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDatabase::class.java, "db").build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}