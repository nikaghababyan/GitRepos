package com.task.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun OnCreate(action: () -> Unit) {
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            action()
        }
    }
}

@Composable
fun OnStart(action: () -> Unit) {
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_START) {
            action()
        }
    }
}

@Composable
fun OnResume(action: () -> Unit) {
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            action()
        }
    }
}

@Composable
fun OnPause(action: () -> Unit) {
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            action()
        }
    }
}


@Composable
fun OnStop(action: () -> Unit) {
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_STOP) {
            action()
        }
    }
}

@Composable
fun OnDestroy(action: () -> Unit) {
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            action()
        }
    }
}

@Composable
fun OnAny(action: () -> Unit) {
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_ANY) {
            action()
        }
    }
}
