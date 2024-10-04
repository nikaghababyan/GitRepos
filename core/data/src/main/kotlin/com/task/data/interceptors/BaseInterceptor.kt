package com.task.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(ACCEPT_TYPE_HEADER, ACCEPT_TYPE)
            .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE)
            .addHeader(CLIENT_DEVICE, DEVICE)
            .addHeader("X-GitHub-Api-Version", "2022-11-28")
            .build()
        return chain.proceed(request)
    }

    companion object {
        internal const val CLIENT_DEVICE = "Client-Device"
        internal const val CONTENT_TYPE_HEADER = "Content-Type"
        internal const val ACCEPT_TYPE_HEADER = "accept"
        internal const val ACCEPT_TYPE = "application/vnd.github+json"
        internal const val CONTENT_TYPE = "application/json"
        internal const val DEVICE = "android"
    }
}