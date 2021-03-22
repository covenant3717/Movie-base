package com.evgenykuksov.recipes.di

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val RxPresentationModule = module {

    single(named(SCHEDULER_UI)) { AndroidSchedulers.mainThread() }
}

internal const val SCHEDULER_UI = "schedulerUi"