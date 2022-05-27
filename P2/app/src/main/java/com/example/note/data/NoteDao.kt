package com.example.note.data

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(note: LocalNote)

    @Update
    fun edit(note: LocalNote)

    @Delete
    fun delete(note: LocalNote)

    @Query("SELECT * From note")
    fun getAll(): List<LocalNote>

    @Query("DELETE From note")
    fun deleteAll()

    @Query("SELECT * FROM note WHERE id=:ID")
    fun get(ID: Int) : LocalNote

}