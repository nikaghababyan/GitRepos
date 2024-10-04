package com.task.presentation

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

@Stable
abstract class CoreViewModel<VIEW_STATE: Any, SIDE_EFFECT: Any, VIEW_INTENT>(defaultState: VIEW_STATE) :
    ContainerHost<VIEW_STATE, SIDE_EFFECT>, ViewModel() {

    override val container = container<VIEW_STATE, SIDE_EFFECT>(defaultState)

    abstract fun onIntent(intent: VIEW_INTENT)
}
