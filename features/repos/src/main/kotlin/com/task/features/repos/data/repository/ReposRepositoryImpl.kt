package com.task.features.repos.data.repository

import com.task.data.room.entities.RepoDbo
import com.task.domain.mappers.Mapper
import com.task.domain.models.Result
import com.task.features.repos.data.datasource.ReposLocalDataSource
import com.task.features.repos.data.datasource.ReposRemoteDataSource
import com.task.features.repos.domain.models.Repo
import com.task.features.repos.domain.repository.ReposRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ReposRepositoryImpl(
    private val reposRemoteDataSource: ReposRemoteDataSource,
    private val reposLocalDataSource: ReposLocalDataSource,
    private val repoToDboMapper: Mapper<Repo, RepoDbo>,
    private val repoDboMapper: Mapper<RepoDbo, Repo>,
    private val dispatcher: CoroutineDispatcher
) : ReposRepository {
    override suspend fun getUserRepos(login: String): Result<List<Repo>> = withContext(dispatcher) {
        when (val result = reposRemoteDataSource.getUserRepos(login)) {
            is Result.Success -> Result.Success(
                result.data.map { repo ->
                    repo.map().copy(
                        branch = reposRemoteDataSource.getRepoBranches(repo.name.orEmpty())
                            .successData?.firstOrNull()?.name.orEmpty()
                    )
                }
            )

            is Result.Error -> result
        }
    }

    override suspend fun saveDownloadedRepo(repo: Repo): Result<Unit> = withContext(dispatcher) {
        reposLocalDataSource.saveDownloadedRepo(repoToDboMapper(repo))
    }

    override fun downloadRepo(repo: Repo): Result<Unit> {
        reposLocalDataSource.downloadRepo(repo.branch, repo.fullName)
        return Result.Success(Unit)
    }

    override suspend fun getDownloadedRepos(): Result<List<Repo>> = withContext(dispatcher) {
        reposLocalDataSource.getDownloadedRepos().map { repos -> repos.map(repoDboMapper) }
    }
}
