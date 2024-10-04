package com.task.features.repos.presentation.repos.mappers

import com.task.domain.mappers.Mapper
import com.task.features.repos.domain.models.Repo
import com.task.features.repos.presentation.repos.models.RepoUI

class RepoToUIMapper : Mapper<Repo, RepoUI> {
    override fun invoke(repo: Repo) = RepoUI(
        id = repo.id,
        name = repo.name,
        owner = RepoUI.OwnerUI(
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