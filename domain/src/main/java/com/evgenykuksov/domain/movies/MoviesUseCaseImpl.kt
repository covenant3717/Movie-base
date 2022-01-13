package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.MoviesData
import kotlinx.coroutines.flow.combine

class MoviesUseCaseImpl(private val repository: MoviesRepository) : MoviesUseCase {

    override fun getAll() = combine(
        getNowPlaying(),
        getPopular(),
        getTopRated()
    ) { listNowPlaying, listPopular, listTopRated ->
        MoviesData(listNowPlaying, listPopular, listTopRated)
    }

    override fun getNowPlaying() = repository.getNowPlaying()

    override fun getPopular() = repository.getPopular()

    override fun getTopRated() = repository.getTopRated()

    override fun getDetails(movieId: Long) = repository.getDetails(movieId)

    override fun getCast(movieId: Long) = repository.getCast(movieId)
}