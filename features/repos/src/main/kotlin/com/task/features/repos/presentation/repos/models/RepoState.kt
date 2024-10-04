package com.task.features.repos.presentation.repos.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

@Immutable
data class RepoState(
    val repos: SnapshotStateList<RepoUI> = mutableStateListOf(),
    val isLoading: Boolean = true
)
