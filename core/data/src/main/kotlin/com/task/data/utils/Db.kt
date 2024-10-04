package com.task.data.utils

import com.task.domain.models.Result

suspend inline fun <reified DbModel> safeDbCall(
    crossinline call: (suspend () -> DbModel)
): Result<DbModel> = try {
    call()?.let {
        Result.Success(it)
    } ?: Result.Error(Exception("Model Not found"))
} catch (e: Throwable) {
    Result.Error(e)
}
