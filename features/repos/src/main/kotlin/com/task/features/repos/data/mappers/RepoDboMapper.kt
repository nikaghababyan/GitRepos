package com.task.features.repos.data.mappers

import com.task.data.room.entities.RepoDbo
import com.task.domain.mappers.Mapper
import com.task.features.repos.domain.models.Repo

class RepoDboMapper : Mapper<RepoDbo, Repo> {
    override fun invoke(repo: RepoDbo) = Repo(
        id = repo.id.toInt(),
        name = repo.name,
        fullName = repo.fullName,
        branch = repo.branch,
        downloadsUrl = repo.downloadsUrl,
        url = repo.url,
        htmlUrl = repo.htmlUrl,
        owner = Repo.Owner(
            login = "",
            avatarUrl = "",
            id = 0
        )
    )
}