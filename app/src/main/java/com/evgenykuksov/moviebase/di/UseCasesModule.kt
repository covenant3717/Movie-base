package com.evgenykuksov.moviebase.di

import com.evgenykuksov.domain.persons.PersonsUseCase
import com.evgenykuksov.domain.persons.PersonsUseCaseImpl
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.MoviesUseCaseImpl
import com.evgenykuksov.domain.profile.ProfileUseCase
import com.evgenykuksov.domain.profile.ProfileUseCaseImpl
import org.koin.dsl.module

internal val useCasesModule = module {

    single<MoviesUseCase> { MoviesUseCaseImpl(get()) }
    single<ProfileUseCase> { ProfileUseCaseImpl(get()) }
    single<PersonsUseCase> { PersonsUseCaseImpl(get()) }
}