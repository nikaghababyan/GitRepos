package com.task.domain.di

import com.task.domain.dispatchers.DispatchersName
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutineModule = module {
    single(named(DispatchersName.IO)) { Dispatchers.IO }

    single(named(DispatchersName.Main)) { Dispatchers.Main }

    single(named(DispatchersName.Default)) { Dispatchers.Default }

    single(named(DispatchersName.Immediate)) { Dispatchers.Main.immediate }
}
