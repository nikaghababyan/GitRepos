package com.task.features.users.presentation.models

import androidx.compose.runtime.Immutable

@Immutable
sealed interface UserIntent {
    data class SearchUser(val query: String) : UserIntent
    data object DownloadClicked : UserIntent
    data class UserClicked(val userId: String) : UserIntent
}
