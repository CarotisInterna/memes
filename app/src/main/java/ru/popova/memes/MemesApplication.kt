package ru.popova.memes

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.popova.memes.db.AppDatabase

class MemesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val addLocalFieldMigration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                with(database) {
                    execSQL("ALTER TABLE meme ADD COLUMN local INTEGER NOT NULL DEFAULT 0")
                    execSQL("UPDATE meme SET local = 0")
                }
            }
        }
        db = Room.databaseBuilder(this, AppDatabase::class.java, "db")
            .addMigrations(addLocalFieldMigration)
            .build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}