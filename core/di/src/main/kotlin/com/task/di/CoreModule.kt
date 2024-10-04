package com.task.di

import com.task.data.di.coreDataModule
import com.task.domain.di.coreDomainModule
import com.task.features.di.featuresModule
import org.koin.dsl.module

val coreModule = module {
    includes(coreDataModule, coreDomainModule, featuresModule)
}
