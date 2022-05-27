package com.example.note.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalNote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
}