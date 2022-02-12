package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.MovieData
import com.evgenykuksov.domain.movies.model.MoviesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class MoviesUseCaseImpl(private val repository: MoviesRepository) : MoviesUseCase {

    override fun getMovies() = combine(
        repository.getUpcoming(),
        repository.getNowPlaying(),
        repository.getPopular(),
        repository.getTopRated()
    ) { listUpcoming, listNowPlaying, listPopular, listTopRated ->
        MoviesData(listUpcoming, listNowPlaying, listPopular, listTopRated)
    }

    override fun getMovieData(movieId: Long): Flow<MovieData> = combine(
        repository.getDetails(movieId),
        repository.getCredits(movieId),
        repository.getTrailers(movieId),
        repository.getImages(movieId),
        repository.getProviders(movieId),
    ) { details, credits, videos, images, providers ->
        MovieData(details, credits, videos, images, providers)
    }
}