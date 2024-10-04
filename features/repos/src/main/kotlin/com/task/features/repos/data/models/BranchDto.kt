package com.task.features.repos.data.models

import com.google.gson.annotations.SerializedName

data class BranchDto(
    @SerializedName("name")
    val name: String?
)
