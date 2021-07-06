package com.evgenykuksov.moviebase.di

import com.evgenykuksov.moviebase.screens.main.MainViewModel
import com.evgenykuksov.moviebase.screens.overview.OverviewViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val ViewModelsModule = module {

    viewModel { MainViewModel() }
    viewModel { OverviewViewModel(get()) }
}