package com.evgenykuksov.data.data.movies.di

import com.evgenykuksov.data.data.movies.MoviesRepositoryImpl
import com.evgenykuksov.data.data.movies.local.memory.MoviesMemoryDataSource
import com.evgenykuksov.data.data.movies.local.memory.MoviesMemoryDataSourceImpl
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteDataSource
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteDataSourceImpl
import com.evgenykuksov.data.data.movies.remote.MoviesApi
import com.evgenykuksov.domain.movies.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal object MoviesModule {

    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

    @Provides
    fun provideMoviesRemoteDataSource(api: MoviesApi): MoviesRemoteDataSource = MoviesRemoteDataSourceImpl(api)

    @Provides
    fun provideMoviesMemoryDataSource(): MoviesMemoryDataSource = MoviesMemoryDataSourceImpl()

    @Provides
    fun provideMoviesRepository(
        remoteDataSource: MoviesRemoteDataSource,
        memoryDataSource: MoviesMemoryDataSource
    ): MoviesRepository = MoviesRepositoryImpl(remoteDataSource, memoryDataSource)
}