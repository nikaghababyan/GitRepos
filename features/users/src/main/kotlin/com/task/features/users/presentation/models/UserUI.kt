package com.task.features.users.presentation.models

import androidx.compose.runtime.Immutable

@Immutable
data class UserUI(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val url: String,
    val reposUrl: String
)
