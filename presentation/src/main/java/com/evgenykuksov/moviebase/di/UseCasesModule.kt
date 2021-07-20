package com.evgenykuksov.moviebase.di

import com.evgenykuksov.domain.movies.MoviesUseCase
import org.koin.dsl.module

internal val UseCasesModule = module {

    single { MoviesUseCase(get()) }
}