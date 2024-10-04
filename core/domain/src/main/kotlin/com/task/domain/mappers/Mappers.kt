package com.task.domain.mappers

fun interface Mapper<I, O> : (I) -> O

fun interface Mappable<O> {
    fun map(): O
}
