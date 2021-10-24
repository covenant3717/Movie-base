package com.evgenykuksov.movie.di

import com.evgenykuksov.core.di.COIL_DEFAULT_LOADER
import com.evgenykuksov.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val FeatureMovieModule = module {

    viewModel { MovieViewModel(get(), get(named(COIL_DEFAULT_LOADER))) }
}