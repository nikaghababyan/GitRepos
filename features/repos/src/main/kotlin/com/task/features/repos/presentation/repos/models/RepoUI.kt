package com.task.features.repos.presentation.repos.models

import androidx.compose.runtime.Immutable

@Immutable
data class RepoUI(
    val id: Int,
    val name: String,
    val owner: OwnerUI,
    val fullName: String,
    val downloadsUrl: String,
    val url: String,
    val htmlUrl: String,
    val branch: String,
) {
    @Immutable
    data class OwnerUI(
        val login: String,
        val avatarUrl: String,
        val id: Int,
    )
}
