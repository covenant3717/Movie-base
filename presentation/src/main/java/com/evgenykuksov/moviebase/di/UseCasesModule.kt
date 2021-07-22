package com.evgenykuksov.moviebase.di

import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.MoviesUseCaseImpl
import org.koin.dsl.module

internal val UseCasesModule = module {

    single<MoviesUseCase> { MoviesUseCaseImpl(get()) }
}