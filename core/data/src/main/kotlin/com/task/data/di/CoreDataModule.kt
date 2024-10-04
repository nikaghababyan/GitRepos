package com.task.data.di

import android.content.Context
import androidx.room.Room
import com.task.data.interceptors.AuthInterceptor
import com.task.data.interceptors.BaseInterceptor
import com.task.data.room.GitReposDb
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreDataModule = module {
    factoryOf(::getOkHttpClientBuilder)
    factoryOf(::provideOkHttpClient)
    singleOf(::getHttpLoggingInterceptor)
    singleOf(::provideAppDataBase)
    singleOf(::provideReposDao)
    singleOf(::AuthInterceptor)
    singleOf(::BaseInterceptor)
}

private fun provideAppDataBase(context: Context): GitReposDb =
    Room.databaseBuilder(context, GitReposDb::class.java, "git_repos_db")
        .fallbackToDestructiveMigration() //TODO remove on release build
        .build()

private fun provideReposDao(db: GitReposDb) = db.reposDao()

private fun getOkHttpClientBuilder(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    baseInterceptor: BaseInterceptor,
    authInterceptor: AuthInterceptor,
): OkHttpClient.Builder = OkHttpClient.Builder().apply {
    addInterceptor(baseInterceptor)
    addInterceptor(authInterceptor)
    addInterceptor(httpLoggingInterceptor)
}


private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor
}

private fun provideOkHttpClient(
    okHttpClientBuilder: OkHttpClient.Builder
): OkHttpClient {
    return okHttpClientBuilder.build()
}

fun <Api> buildApi(
    api: Class<Api>,
    okHttpClient: OkHttpClient,
    baseUrl: String
): Api {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(api)
}
