package com.task.features.di

import com.task.features.repos.di.reposModule
import com.task.features.users.di.usersModule
import org.koin.dsl.module

val featuresModule = module {
    includes(usersModule, reposModule)
}
