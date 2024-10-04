package com.task.features.repos.data.models

import com.google.gson.annotations.SerializedName
import com.task.domain.extensions.orDefault
import com.task.domain.mappers.Mappable
import com.task.features.repos.domain.models.Repo

data class RepoDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("owner")
    val owner: OwnerDto?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("downloads_url")
    val downloadsUrl: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?
): Mappable<Repo> {
    data class OwnerDto(
        @SerializedName("login")
        val login: String?,
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("id")
        val id: Int?,
    )

    override fun map() = Repo(
        id = id.orDefault(0),
        name = name.orEmpty(),
        owner = Repo.Owner(
            login = owner?.login.orEmpty(),
            avatarUrl = owner?.avatarUrl.orEmpty(),
            id = owner?.id.orDefault(0)
        ),
        fullName = fullName.orEmpty(),
        downloadsUrl = downloadsUrl.orEmpty(),
        url = url.orEmpty(),
        htmlUrl = htmlUrl.orEmpty(),
        branch = ""
    )
}