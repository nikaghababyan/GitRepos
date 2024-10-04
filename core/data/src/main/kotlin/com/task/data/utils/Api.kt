package com.task.data.utils

import com.task.domain.models.Result
import retrofit2.HttpException
import retrofit2.Response

suspend inline fun <reified ApiModel> safeApiCall(
    crossinline call: (suspend () -> Response<ApiModel>)
): Result<ApiModel> = try {
    val response = call()
    val body = response.body()
    if (response.isSuccessful) {
        if (body is Unit) Result.Success(body)
        if (body != null) Result.Success(body)
        else Result.Error(
            HttpException(response),
            response.code(),
            response.message()
        )
    } else Result.Error(
        HttpException(response),
        response.code(),
        response.message()
    )
} catch (e: HttpException) {
    Result.Error(e, e.code(), e.message())
} catch (e: Throwable) {
    Result.Error(e)
}

