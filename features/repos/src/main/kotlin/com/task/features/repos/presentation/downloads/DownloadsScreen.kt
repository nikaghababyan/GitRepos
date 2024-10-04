package com.task.features.repos.presentation.downloads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.task.features.repos.presentation.downloads.models.DownloadsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DownloadsScreen(modifier: Modifier = Modifier) {
    val viewModel: DownloadsViewModel = koinViewModel()
    val state by viewModel.container.stateFlow.collectAsState()

    DownloadsScreenContent(
        modifier = modifier,
        state = state
    )
}

@Composable
internal fun DownloadsScreenContent(
    modifier: Modifier = Modifier,
    state: DownloadsState = DownloadsState()
) {
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
                            .padding(8.dp),
                        text = repo.fullName
                    )

                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Default.Book,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}
