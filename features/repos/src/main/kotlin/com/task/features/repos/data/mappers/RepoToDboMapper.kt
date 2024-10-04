package com.task.features.repos.data.mappers

import com.task.data.room.entities.RepoDbo
import com.task.domain.mappers.Mapper
import com.task.features.repos.domain.models.Repo

class RepoToDboMapper : Mapper<Repo, RepoDbo> {
    override fun invoke(repo: Repo) = RepoDbo(
        id = repo.id.toString(),
        name = repo.name,
        fullName = repo.fullName,
        downloadsUrl = repo.downloadsUrl,
        url = repo.url,
        htmlUrl = repo.htmlUrl,
        branch = repo.branch,
    )
}