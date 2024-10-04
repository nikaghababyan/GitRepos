package com.task.features.repos.domain.models

data class Repo(
    val id: Int,
    val name: String,
    val owner: Owner,
    val fullName: String,
    val downloadsUrl: String,
    val url: String,
    val htmlUrl: String,
    val branch: String,
) {
    data class Owner(
        val login: String,
        val avatarUrl: String,
        val id: Int,
    )
}
