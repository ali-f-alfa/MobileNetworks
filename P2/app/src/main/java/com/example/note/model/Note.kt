package com.example.note.model

import java.io.Serializable
import java.time.LocalDateTime

data class Note (
    val id: Int?,
    var title: String,
    var description: String,
    var lastModifiedDate: LocalDateTime?
): Serializable