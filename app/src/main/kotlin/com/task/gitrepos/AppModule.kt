package com.task.gitrepos

import com.task.data.config.Config
import org.koin.dsl.module

val appModule = module {
    single<Config> { ConfigImpl() }
}
