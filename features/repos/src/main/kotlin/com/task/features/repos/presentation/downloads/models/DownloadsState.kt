package com.task.features.repos.presentation.downloads.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.task.features.repos.presentation.repos.models.RepoUI

@Immutable
data class DownloadsState(
    val repos: SnapshotStateList<RepoUI> = mutableStateListOf()
)
