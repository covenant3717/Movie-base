package com.evgenykuksov.moviebase.di

import com.evgenykuksov.domain.movies.MoviesRepository
import com.evgenykuksov.domain.persons.PersonsUseCase
import com.evgenykuksov.domain.persons.PersonsUseCaseImpl
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.MoviesUseCaseImpl
import com.evgenykuksov.domain.persons.PersonsRepository
import com.evgenykuksov.domain.profile.ProfileRepository
import com.evgenykuksov.domain.profile.ProfileUseCase
import com.evgenykuksov.domain.profile.ProfileUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.koin.dsl.module

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    fun provideMoviesUseCase(repository: MoviesRepository): MoviesUseCase = MoviesUseCaseImpl(repository)

    @Provides
    fun provideProfileUseCase(repository: ProfileRepository): ProfileUseCase = ProfileUseCaseImpl(repository)

    @Provides
    fun providePersonsUseCase(repository: PersonsRepository): PersonsUseCase = PersonsUseCaseImpl(repository)
}