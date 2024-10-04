package com.task.features.repos.presentation.repos.mappers

import com.task.domain.mappers.Mapper
import com.task.features.repos.domain.models.Repo
import com.task.features.repos.presentation.repos.models.RepoUI

class RepoUIMapper: Mapper<RepoUI, Repo> {
    override fun invoke(repo: RepoUI) = Repo(
        id = repo.id,
        name = repo.name,
        owner = Repo.Owner(
            login = repo.owner.login,
            avatarUrl = repo.owner.avatarUrl,
            id = repo.owner.id
        ),
        fullName = repo.fullName,
        downloadsUrl = repo.downloadsUrl,
        url = repo.url,
        htmlUrl = repo.htmlUrl,
        branch = repo.branch
    )
}