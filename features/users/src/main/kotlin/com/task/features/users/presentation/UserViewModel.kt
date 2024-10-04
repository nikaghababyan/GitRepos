package com.task.features.users.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.task.domain.mappers.Mapper
import com.task.features.users.domain.models.User
import com.task.features.users.domain.repository.UserRepository
import com.task.features.users.presentation.models.UserEffect
import com.task.features.users.presentation.models.UserIntent
import com.task.features.users.presentation.models.UserState
import com.task.features.users.presentation.models.UserUI
import com.task.presentation.CoreViewModel
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class UserViewModel(
    private val userRepository: UserRepository,
    private val userMapper: Mapper<User, UserUI>
) : CoreViewModel<UserState, UserEffect, UserIntent>(UserState()) {

    override fun onIntent(intent: UserIntent) = intent {
        when (intent) {
            is UserIntent.DownloadClicked -> postSideEffect(UserEffect.NavigateToDownloads)
            is UserIntent.SearchUser -> searchUser(intent.query)
            is UserIntent.UserClicked -> postSideEffect(UserEffect.NavigateToRepos(intent.userId))
        }
    }

    private fun searchUser(query: String) = intent {
        val flow = userRepository.getPagingDataFlow(query)
            .map { pagingData ->
                pagingData.map {
                    userMapper(it)
                }
            }
            .cachedIn(viewModelScope)
        reduce { state.copy(users = flow) }
    }
}
