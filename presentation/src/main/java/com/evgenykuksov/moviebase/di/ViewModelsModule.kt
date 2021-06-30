package com.evgenykuksov.moviebase.di

import com.evgenykuksov.moviebase.screens.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val ViewModelsModule = module {

    viewModel { MainViewModel(get()) }
}