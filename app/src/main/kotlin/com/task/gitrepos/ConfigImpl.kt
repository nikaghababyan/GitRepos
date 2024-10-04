package com.task.gitrepos

import com.task.data.config.Config

class ConfigImpl : Config{
    override val token: String
        get() = BuildConfig.GIT_TOKEN

    override val baseUrl: String
        get() = BuildConfig.BASE_URL
}
