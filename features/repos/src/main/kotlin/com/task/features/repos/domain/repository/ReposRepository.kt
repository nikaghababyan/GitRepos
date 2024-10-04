package com.task.features.repos.domain.repository

import com.task.domain.models.Result
import com.task.features.repos.domain.models.Repo

interface ReposRepository {

    suspend fun getUserRepos(login: String): Result<List<Repo>>

    suspend fun saveDownloadedRepo(repo: Repo): Result<Unit>

    fun downloadRepo(repo: Repo): Result<Unit>

    suspend fun getDownloadedRepos(): Result<List<Repo>>
}
