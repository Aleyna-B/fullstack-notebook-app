package com.aleyna.notebookapp_v1.models

import java.time.LocalDate

data class NotesResponseModel(      //request type for showUserNotes
    val id: Long,
    val note: String,
    val title: String,
    val creationTime: String,
    val user_id: Long
)