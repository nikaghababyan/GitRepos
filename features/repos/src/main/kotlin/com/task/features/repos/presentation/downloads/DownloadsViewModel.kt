package com.task.features.repos.presentation.downloads

import androidx.compose.runtime.toMutableStateList
import com.task.domain.mappers.Mapper
import com.task.features.repos.domain.models.Repo
import com.task.features.repos.domain.repository.ReposRepository
import com.task.features.repos.presentation.downloads.models.DownloadsEffect
import com.task.features.repos.presentation.downloads.models.DownloadsIntent
import com.task.features.repos.presentation.downloads.models.DownloadsState
import com.task.features.repos.presentation.repos.models.RepoUI
import com.task.presentation.CoreViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class DownloadsViewModel(
    private val reposRepository: ReposRepository,
    private val repoToUIMapper: Mapper<Repo, RepoUI>
): CoreViewModel<DownloadsState, DownloadsIntent, DownloadsEffect>(DownloadsState()) {
    init {
        intent {
            val repos = reposRepository.getDownloadedRepos().successData.orEmpty()
                .map(repoToUIMapper)
                .toMutableStateList()
            reduce { state.copy(repos = repos) }
        }
    }

    override fun onIntent(intent: DownloadsEffect) {
        when (intent) {
            else -> Unit
        }
    }
}