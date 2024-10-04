package com.task.features.users.domain.models

data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val url: String,
    val reposUrl: String
)
