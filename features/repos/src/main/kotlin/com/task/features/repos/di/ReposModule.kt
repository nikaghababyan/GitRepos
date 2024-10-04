package com.task.features.repos.di

import com.task.data.config.Config
import com.task.data.di.buildApi
import com.task.domain.dispatchers.DispatchersName
import com.task.features.repos.data.api.ReposApi
import com.task.features.repos.data.datasource.ReposLocalDataSource
import com.task.features.repos.data.datasource.ReposRemoteDataSource
import com.task.features.repos.data.mappers.RepoDboMapper
import com.task.features.repos.data.mappers.RepoToDboMapper
import com.task.features.repos.data.repository.ReposRepositoryImpl
import com.task.features.repos.domain.repository.ReposRepository
import com.task.features.repos.domain.usecase.DownloadRepoUseCase
import com.task.features.repos.domain.usecase.DownloadRepoUseCaseImpl
import com.task.features.repos.presentation.downloads.DownloadsViewModel
import com.task.features.repos.presentation.repos.RepoViewModel
import com.task.features.repos.presentation.repos.mappers.RepoToUIMapper
import com.task.features.repos.presentation.repos.mappers.RepoUIMapper
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val reposModule = module {
    singleOf(::provideReposApi)
    factoryOf(::RepoToDboMapper)
    factoryOf(::RepoDboMapper)
    singleOf(::ReposRemoteDataSource)
    singleOf(::ReposLocalDataSource)
    single<ReposRepository> {
        ReposRepositoryImpl(
            reposRemoteDataSource = get(),
            reposLocalDataSource = get(),
            repoToDboMapper = get<RepoToDboMapper>(),
            repoDboMapper = get<RepoDboMapper>(),
            dispatcher = get(named(DispatchersName.IO)),
        )
    }
    factoryOf(::RepoToUIMapper)
    factoryOf(::RepoUIMapper)
    factoryOf(::DownloadRepoUseCaseImpl) { bind<DownloadRepoUseCase>() }
    viewModel { (userId: String) ->
        RepoViewModel(
            userid = userId,
            reposRepository = get(),
            repoToUIMapper = get<RepoToUIMapper>(),
            repoUIMapper = get<RepoUIMapper>(),
            downloadRepoUseCase = get()
        )
    }
    viewModel {
        DownloadsViewModel(
            reposRepository = get(),
            repoToUIMapper = get<RepoToUIMapper>(),
        )
    }
}

fun provideReposApi(
    okHttpClient: OkHttpClient,
    config: Config
): ReposApi =
    buildApi(ReposApi::class.java, okHttpClient, config.baseUrl)