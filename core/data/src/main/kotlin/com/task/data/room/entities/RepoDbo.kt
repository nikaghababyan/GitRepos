package com.task.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos_table")
data class RepoDbo(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val fullName: String,
    val branch: String,
    val downloadsUrl: String,
    val url: String,
    val htmlUrl: String
)
