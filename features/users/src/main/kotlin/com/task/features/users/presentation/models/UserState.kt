package com.task.features.users.presentation.models

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class UserState(
    val users: Flow<PagingData<UserUI>> = emptyFlow()
)
