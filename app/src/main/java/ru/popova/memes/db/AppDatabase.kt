package ru.popova.memes.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Meme::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memeDao(): MemeDao
}