package com.task.features.repos.presentation.repos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.task.features.repos.presentation.repos.models.RepoIntent
import com.task.features.repos.presentation.repos.models.RepoState
import com.task.features.repos.presentation.repos.models.RepoUI
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ReposScreen(modifier: Modifier = Modifier, userId: String) {
    val viewModel: RepoViewModel = koinViewModel() {
        parametersOf(userId)
    }
    val state by viewModel.container.stateFlow.collectAsState()

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp))
        }
    } else {
        RepoScreenContent(
            modifier = modifier,
            state = state,
            onRepoClick = { viewModel.onIntent(RepoIntent.DownloadRepo(it)) }
        )
    }
}

@Composable
internal fun RepoScreenContent(
    modifier: Modifier = Modifier,
    state: RepoState = RepoState(),
    onRepoClick: (RepoUI) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(state.repos) { repo ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .clickable { uriHandler.openUri(repo.htmlUrl) }
                            .padding(8.dp),
                        text = repo.fullName
                    )

                    Icon(
                        modifier = Modifier.clickable { onRepoClick(repo) },
                        imageVector = Icons.Default.Download,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}
