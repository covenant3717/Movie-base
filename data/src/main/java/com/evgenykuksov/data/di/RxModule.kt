package com.evgenykuksov.data.di

import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val RxModule = module {

    single(named(SCHEDULER_IO)) { Schedulers.io() }
}

internal const val SCHEDULER_IO = "schedulerIo"