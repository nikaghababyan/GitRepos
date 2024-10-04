package com.task.features.users.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.task.features.users.data.api.UserApi
import com.task.features.users.data.datasource.UserPagingSource
import com.task.features.users.data.datasource.UserPagingSource.Companion.DEFAULT_PAGE_SIZE
import com.task.features.users.domain.models.User
import com.task.features.users.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override fun getPagingDataFlow(query: String): Flow<PagingData<User>> =
        Pager(
            config = PagingConfig(
                DEFAULT_PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = DEFAULT_PAGE_SIZE,
                initialLoadSize = DEFAULT_PAGE_SIZE
            )
        ) {
            UserPagingSource(query, userApi)
        }.flow.map { pagingData -> pagingData.map { it.map() } }.flowOn(dispatcher)
}

