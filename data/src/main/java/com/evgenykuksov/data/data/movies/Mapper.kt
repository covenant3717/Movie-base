package com.evgenykuksov.data.data.movies

import com.evgenykuksov.data.extensions.orNegativeDefault
import com.evgenykuksov.data.data.movies.remote.model.MovieRemote
import com.evgenykuksov.data.extensions.orZero
import com.evgenykuksov.data.util.getOriginalImageUrl
import com.evgenykuksov.domain.recipes.model.Movie

fun MovieRemote.toDomain() = Movie(
    id = id.orNegativeDefault(),
    backdropPath = getOriginalImageUrl(backdropPath.orEmpty()),
    genreIds = genreIds.orEmpty(),
    title = title.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity.orZero(),
    posterPath = getOriginalImageUrl(posterPath.orEmpty()),
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage.orZero(),
    voteCount = voteCount.orZero()
)