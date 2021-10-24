package com.evgenykuksov.actor.di

import com.evgenykuksov.actor.ActorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FeatureActorModule = module {

    viewModel { ActorViewModel(get()) }
}