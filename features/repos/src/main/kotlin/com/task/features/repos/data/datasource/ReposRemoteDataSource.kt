package com.task.features.repos.data.datasource

import com.task.data.utils.safeApiCall
import com.task.features.repos.data.api.ReposApi

class ReposRemoteDataSource(
    private val reposApi: ReposApi
) {

    suspend fun getUserRepos(login: String) = safeApiCall {
        reposApi.getUserRepos(login)
    }

    suspend fun getRepoBranches(repo: String) = safeApiCall {
        reposApi.getRepoBranches(repo)
    }
}