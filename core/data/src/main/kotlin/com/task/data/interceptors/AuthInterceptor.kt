package com.task.data.interceptors

import com.task.data.config.Config
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val config: Config
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer ${config.token}")
            .build()
        return chain.proceed(request)
    }
}