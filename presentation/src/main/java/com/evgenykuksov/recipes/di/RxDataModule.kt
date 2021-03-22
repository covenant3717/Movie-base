package com.evgenykuksov.recipes.di

import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val RxModule = module {

    single(named(SCHEDULER_IO)) { Schedulers.io() }
}

internal const val SCHEDULER_IO = "schedulerIo"