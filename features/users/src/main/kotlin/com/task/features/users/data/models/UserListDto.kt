package com.task.features.users.data.models

import com.google.gson.annotations.SerializedName

data class UserListDto(
    @SerializedName("items")
    val users: List<UserDto>?
)