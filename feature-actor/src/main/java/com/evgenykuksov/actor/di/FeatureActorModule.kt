package com.evgenykuksov.actor.di

import com.evgenykuksov.actor.ActorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureActorModule = module {

    viewModel { (actorId: Long) -> ActorViewModel(actorId, get()) }
}