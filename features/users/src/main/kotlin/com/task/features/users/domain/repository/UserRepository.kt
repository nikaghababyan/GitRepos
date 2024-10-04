package com.task.features.users.domain.repository

import androidx.paging.PagingData
import com.task.features.users.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getPagingDataFlow(query: String): Flow<PagingData<User>>
}
