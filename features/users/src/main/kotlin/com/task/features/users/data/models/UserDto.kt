package com.task.features.users.data.models

import com.google.gson.annotations.SerializedName
import com.task.domain.extensions.orDefault
import com.task.domain.mappers.Mappable
import com.task.features.users.domain.models.User

data class UserDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("repos_url")
    val reposUrl: String?
) : Mappable<User> {

    override fun map() = User(
        id = id.orDefault(0),
        login = login.orEmpty(),
        avatarUrl = avatarUrl.orEmpty(),
        url = url.orEmpty(),
        reposUrl = reposUrl.orEmpty()
    )
}
