package com.task.features.users.presentation.models

sealed interface UserEffect {
    data object NavigateToDownloads : UserEffect
    data class NavigateToRepos(val userId: String) : UserEffect
}
