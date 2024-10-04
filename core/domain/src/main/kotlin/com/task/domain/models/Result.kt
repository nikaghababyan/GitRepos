package com.task.domain.models

sealed interface Result<out T> {

    data class Success<out T>(val data: T) : Result<T>

    data class Error(val error: Throwable, val code: Int? = null, val message: String = "") :
        Result<Nothing>

    fun <O> map(mapper: (T) -> O): Result<O> = when (this) {
        is Success -> Success(mapper(data))

        is Error -> Error(error, code)
    }

    val successData: T?
        get() = if (this is Success) data else null

    fun <O> mapData(default: O, mapper: (T) -> O): O = when (this) {
        is Success -> mapper(data)
        is Error -> default
    }

    fun <O> mapData(mapper: (T) -> O): O? = when (this) {
        is Success -> mapper(data)
        is Error -> null
    }

    suspend fun onSuccess(action: suspend (T) -> Unit): Result<T> {
        when (this) {
            is Success -> action(data)
            is Error -> Unit
        }
        return this
    }

    suspend fun onError(action: suspend (Error) -> Unit): Result<T> {
        when (this) {
            is Success -> Unit
            is Error -> action(this)
        }
        return this
    }
}
