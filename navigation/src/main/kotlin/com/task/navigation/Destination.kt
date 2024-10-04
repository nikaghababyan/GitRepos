package com.task.navigation

import androidx.compose.runtime.Stable

@Stable
sealed class Destination(val route: String) {

    @Stable
    data object Downloads : Destination("downloads")

    @Stable
    data object Users : Destination("users")

    @Stable
    data object Repos : Destination("repos/{userid}") {
        fun navigationRoute(userid: String) = "repos/$userid"
    }
}
