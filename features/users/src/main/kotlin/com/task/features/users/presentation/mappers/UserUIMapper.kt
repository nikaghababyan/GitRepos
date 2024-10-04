package com.task.features.users.presentation.mappers

import com.task.domain.mappers.Mapper
import com.task.features.users.domain.models.User
import com.task.features.users.presentation.models.UserUI

class UserUIMapper : Mapper<User, UserUI> {
    override fun invoke(user: User) = UserUI(
        id = user.id,
        login = user.login,
        avatarUrl = user.avatarUrl,
        url = user.url,
        reposUrl = user.reposUrl
    )
}