package com.evgenykuksov.data.data.movies

import com.evgenykuksov.data.extensions.orNegativeDefault
import com.evgenykuksov.data.data.movies.remote.model.MovieRemote
import com.evgenykuksov.data.extensions.orZero
import com.evgenykuksov.domain.recipes.model.Movie

fun MovieRemote.toDomain() = Movie(
    id = id.orNegativeDefault(),
    backdropPath = backdropPath.orEmpty(),
    genreIds = genreIds.orEmpty(),
    title = title.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity.orZero(),
    posterPath = posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage.orZero(),
    voteCount = voteCount.orZero()
)