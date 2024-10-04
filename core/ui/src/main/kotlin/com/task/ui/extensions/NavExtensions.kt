package com.task.ui.extensions

import androidx.navigation.NavHostController

fun NavHostController.navigateByArgumentWithSaveStateHandle(root: String, key: String, value: Any) =
    this.currentBackStackEntry?.savedStateHandle?.set(key, value).also {
        navigate(root)
    }

fun <T> NavHostController.getArgumentByKeyWithSaveStateHandle(key: String): T? {
    val savedStateHandle = this.previousBackStackEntry?.savedStateHandle
    return savedStateHandle?.get<T>(key).also { savedStateHandle?.remove<T>(key) }
}
