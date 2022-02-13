package com.evgenykuksov.actor.di

import com.evgenykuksov.actor.ActorViewModel
import com.evgenykuksov.core.di.COIL_DEFAULT_LOADER
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureActorModule = module {

    viewModel { (actorId: Long) -> ActorViewModel(actorId, get(), get(named(COIL_DEFAULT_LOADER))) }
}