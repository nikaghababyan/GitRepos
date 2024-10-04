package com.task.features.repos.data.api

import com.task.features.repos.data.models.BranchDto
import com.task.features.repos.data.models.RepoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposApi {

    @GET("users/{login}/repos")
    suspend fun getUserRepos(@Path("login") login: String): Response<List<RepoDto>>

    @GET("repos/{repo}/branches")
    suspend fun getRepoBranches(@Path("repo") repo: String): Response<List<BranchDto>>
}