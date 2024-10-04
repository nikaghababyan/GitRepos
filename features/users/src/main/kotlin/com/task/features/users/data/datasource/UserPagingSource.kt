package com.task.features.users.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.data.utils.safeApiCall
import com.task.features.users.data.api.UserApi
import com.task.features.users.data.models.UserDto

class UserPagingSource(
    val query: String,
    private val userApi: UserApi
): PagingSource<Int, UserDto>() {
    override fun getRefreshKey(state: PagingState<Int, UserDto>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDto> {
        try {
            val nextPage = params.key ?: 1
            val users = if(query.isNotEmpty()) safeApiCall {
                userApi.searchUsers(query, DEFAULT_PAGE_SIZE, nextPage)
            }.successData?.users.orEmpty() else emptyList()

            return LoadResult.Page(
                data = users,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (users.isEmpty()) null else nextPage.plus(1)
            )
        } catch (t: Throwable) {
            return LoadResult.Error(t)
        }
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 30
    }
}
