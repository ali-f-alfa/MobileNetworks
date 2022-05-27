package com.example.note.model

import java.time.LocalDateTime

data class Note (
    val title: String,
    val description: String,
    val lastModifiedDate: LocalDateTime?
)