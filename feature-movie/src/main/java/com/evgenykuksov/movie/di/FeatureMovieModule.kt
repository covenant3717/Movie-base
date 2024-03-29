package com.evgenykuksov.movie.di

import com.evgenykuksov.core.di.COIL_DEFAULT_LOADER
import com.evgenykuksov.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureMovieModule = module {

    viewModel { (movieId: Long) -> MovieViewModel(movieId, get(), get(), get(named(COIL_DEFAULT_LOADER))) }
}