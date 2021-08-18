package com.evgenykuksov.moviebase.di

import com.evgenykuksov.moviebase.screens.main.MainViewModel
import com.evgenykuksov.moviebase.screens.movie.MovieViewModel
import com.evgenykuksov.moviebase.screens.overview.OverviewViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val ViewModelsModule = module {

    viewModel { MainViewModel() }
    viewModel { OverviewViewModel(get(), get(), get(named(COIL_DEFAULT_LOADER)), get(named(COIL_GIF_LOADER))) }
    viewModel { MovieViewModel(get(), get(named(COIL_DEFAULT_LOADER))) }
}