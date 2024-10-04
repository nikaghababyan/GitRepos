package com.task.features.users.di

import com.task.data.config.Config
import com.task.data.di.buildApi
import com.task.domain.dispatchers.DispatchersName
import com.task.features.users.data.api.UserApi
import com.task.features.users.data.repository.UserRepositoryImpl
import com.task.features.users.domain.repository.UserRepository
import com.task.features.users.presentation.UserViewModel
import com.task.features.users.presentation.mappers.UserUIMapper
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val usersModule = module {
    singleOf(::provideUsersApi)
    single<UserRepository> {
        UserRepositoryImpl(
            userApi = get(),
            dispatcher = get(named(DispatchersName.IO))
        )
    }
    factoryOf(::UserUIMapper)
    viewModel {
        UserViewModel(
            userRepository = get(),
            userMapper = get<UserUIMapper>()
        )
    }
}

fun provideUsersApi(
    okHttpClient: OkHttpClient,
    config: Config
) : UserApi =
    buildApi(UserApi::class.java, okHttpClient, config.baseUrl)
