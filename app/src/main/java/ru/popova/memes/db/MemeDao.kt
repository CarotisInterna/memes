package ru.popova.memes.db

import androidx.room.*

@Dao
interface MemeDao {
    @Query("SELECT * FROM meme")
    fun getAll(): List<Meme>

    @Query("SELECT * FROM meme WHERE local = 1")
    fun getLocal(): List<Meme>

    @Query("SELECT count(*) FROM meme")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj: Meme): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj: List<Meme>): List<Long>

    @Update
    fun update(obj: Meme)

    @Update
    fun update(obj: List<Meme>)

    @Transaction
    fun insertOrUpdate(obj: Meme) {
        val id = insert(obj)
        if (id == -1L) update(obj)
    }

    @Transaction
    fun insertOrUpdate(objList: List<Meme>) {
        val insertResult = insert(objList)
        val updateList = mutableListOf<Meme>()

        for (i in insertResult.indices) if (insertResult[i] == -1L) updateList.add(objList[i])

        if (updateList.isNotEmpty()) update(updateList)
    }
}