package com.example.note.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.note.model.Note
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NoteCacheMapper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun mapFrom(value: Note): LocalNote = with(value){
        LocalNote(
            id = id,
            title = title,
            description = description,
            lastModifiedDate = lastModifiedDate.toString()
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun mapTo(value: LocalNote): Note = with(value){
        Note(
            id = id,
            title = title,
            description = description,
            lastModifiedDate = LocalDateTime.parse(lastModifiedDate)
        )
    }
}