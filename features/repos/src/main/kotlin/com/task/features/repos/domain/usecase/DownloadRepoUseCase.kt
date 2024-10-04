package com.task.features.repos.domain.usecase

import com.task.features.repos.domain.models.Repo
import com.task.features.repos.domain.repository.ReposRepository
import com.task.domain.models.Result

interface DownloadRepoUseCase {
    suspend operator fun invoke(repo: Repo): Result<Unit>
}

class DownloadRepoUseCaseImpl(
    private val reposRepository: ReposRepository
) : DownloadRepoUseCase {
    override suspend fun invoke(repo: Repo): Result<Unit> {
        reposRepository.saveDownloadedRepo(repo)
        return reposRepository.downloadRepo(repo)
    }
}
