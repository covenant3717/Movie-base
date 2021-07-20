package com.evgenykuksov.domain.movies

class MoviesUseCase(private val repository: MoviesRepository) {

    fun getPopular() = repository.getPopular()

    fun getTopRated() = repository.getTopRated()
}