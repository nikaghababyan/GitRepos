package com.task.features.users.data.api

import com.task.features.users.data.models.UserDto
import com.task.features.users.data.models.UserListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
    ): Response<UserListDto>
}