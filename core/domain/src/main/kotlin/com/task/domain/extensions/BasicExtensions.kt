package com.task.domain.extensions

fun <T: Any> T?.orDefault(default: T): T { return this ?: default }

inline fun <reified T: Enum<T>> String.tryGetEnumValue() =
    try {
        enumValueOf(this)
    } catch (t: Throwable) {
        enumValues<T>()[0]
    }
