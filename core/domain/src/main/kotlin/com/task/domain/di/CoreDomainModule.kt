package com.task.domain.di

import org.koin.dsl.module

val coreDomainModule = module {
    includes(coroutineModule)
}
