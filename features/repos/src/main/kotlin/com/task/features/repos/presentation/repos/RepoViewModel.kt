package com.task.features.repos.presentation.repos

import androidx.compose.runtime.toMutableStateList
import com.task.domain.mappers.Mapper
import com.task.features.repos.domain.models.Repo
import com.task.features.repos.domain.repository.ReposRepository
import com.task.features.repos.domain.usecase.DownloadRepoUseCase
import com.task.features.repos.presentation.repos.models.RepoEffect
import com.task.features.repos.presentation.repos.models.RepoIntent
import com.task.features.repos.presentation.repos.models.RepoState
import com.task.features.repos.presentation.repos.models.RepoUI
import com.task.presentation.CoreViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class RepoViewModel(
    userid: String,
    private val reposRepository: ReposRepository,
    private val downloadRepoUseCase: DownloadRepoUseCase,
    private val repoUIMapper: Mapper<RepoUI, Repo>,
    repoToUIMapper: Mapper<Repo, RepoUI>
) : CoreViewModel<RepoState, RepoEffect, RepoIntent>(RepoState()) {

    init {
        intent {
            val repos = reposRepository.getUserRepos(userid).successData.orEmpty()
                .map { repoToUIMapper(it) }
                .toMutableStateList()
            reduce { state.copy(repos = repos, isLoading = false) }
        }
    }

    override fun onIntent(intent: RepoIntent) {
        when (intent) {
            is RepoIntent.DownloadRepo -> intent { downloadRepoUseCase(repoUIMapper(intent.repo)) }
        }
    }
}
