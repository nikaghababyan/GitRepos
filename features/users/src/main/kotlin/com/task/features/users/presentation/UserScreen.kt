package com.task.features.users.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.task.features.users.R
import com.task.features.users.presentation.models.UserEffect
import com.task.features.users.presentation.models.UserIntent
import com.task.features.users.presentation.models.UserState
import com.task.features.users.presentation.models.UserUI
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    onNavigateToDownloads: () -> Unit,
    onNavigateToRepos: (String) -> Unit,
) {
    val viewModel: UserViewModel = getViewModel()
    val state by viewModel.container.stateFlow.collectAsState()

    viewModel.collectSideEffect { effect ->
        when (effect) {
            is UserEffect.NavigateToDownloads -> onNavigateToDownloads()
            is UserEffect.NavigateToRepos -> onNavigateToRepos(effect.userId)
        }
    }

    UserScreenContent(
        modifier = modifier,
        state = state,
        onSearch = { viewModel.onIntent(UserIntent.SearchUser(it)) },
        onDownloadClicked = { viewModel.onIntent(UserIntent.DownloadClicked) },
        onUserClicked = { viewModel.onIntent(UserIntent.UserClicked(it)) },
    )
}

@Composable
internal fun UserScreenContent(
    modifier: Modifier,
    state: UserState,
    onSearch: (String) -> Unit,
    onDownloadClicked: () -> Unit,
    onUserClicked: (String) -> Unit,
) {
    var search by rememberSaveable { mutableStateOf("") }
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.users_header),
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable(onClick = onDownloadClicked),
                        imageVector = Icons.Default.CloudDownload,
                        contentDescription = null
                    )
                }

                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it; onSearch(it) },
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium
                )
            }
        }
    ) { paddingValues ->
        val users = state.users.collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (users.loadState.refresh is LoadState.Loading && search.isNotEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(48.dp))
                    }
                }
            }

            items(users.itemCount) {
                users[it]?.let { user ->
                    UserItem(
                        user = user,
                        onUserClicked = onUserClicked
                    )
                }
            }

            if (users.loadState.append is LoadState.Loading && search.isNotEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(48.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserItem(user: UserUI, onUserClicked: (String) -> Unit) {
    ElevatedCard(
        onClick = { onUserClicked(user.login) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(shape = CardDefaults.elevatedShape),
                painter = rememberAsyncImagePainter(user.avatarUrl),
                contentScale = ContentScale.Crop,
                contentDescription = "userPhoto"
            )

            Text(
                text = user.login,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
