package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.MovieData
import com.evgenykuksov.domain.movies.model.MoviesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class MoviesUseCaseImpl(private val repository: MoviesRepository) : MoviesUseCase {

    override fun getMovies() = combine(
        repository.getNowPlaying(),
        repository.getPopular(),
        repository.getTopRated()
    ) { listNowPlaying, listPopular, listTopRated ->
        MoviesData(listNowPlaying, listPopular, listTopRated)
    }

    override fun getMovieData(movieId: Long): Flow<MovieData> = combine(
        repository.getDetails(movieId),
        repository.getCast(movieId),
        repository.getTrailers(movieId),
        repository.getImages(movieId),
    ) { details, cast, videos, images ->
        MovieData(details, cast, videos, images)
    }
}