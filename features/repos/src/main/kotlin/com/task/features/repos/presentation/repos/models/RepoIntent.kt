package com.task.features.repos.presentation.repos.models

sealed interface RepoIntent {
    data class DownloadRepo(val repo: RepoUI): RepoIntent
}
