package com.example.note

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.note.data.AppDatabase

class BaseApplication: Application() {

    companion object{
        const val DATABASE_NAME = "database"
        lateinit var database: AppDatabase
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("111111111111111", "11")

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
        Log.d("22222222222", "11")

    }
}