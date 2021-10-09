package com.evgenykuksov.data.data.movies

import com.evgenykuksov.core.extensions.orNegativeDefault
import com.evgenykuksov.core.extensions.orZero
import com.evgenykuksov.data.data.movies.remote.model.ActorRemote
import com.evgenykuksov.data.data.movies.remote.model.GenreRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieDetailsRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieRemote
import com.evgenykuksov.data.util.getOriginalImageUrl
import com.evgenykuksov.domain.movies.model.Actor
import com.evgenykuksov.domain.movies.model.Genre
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.domain.movies.model.MovieDetails

internal fun MovieRemote.toDomain() = Movie(
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

internal fun MovieDetailsRemote.toDomain() = MovieDetails(
    id = id.orNegativeDefault(),
    title = title.orEmpty(),
    backdropPath = getOriginalImageUrl(backdropPath.orEmpty()),
    budget = budget.orZero(),
    genres = genres?.map { it.toDomain() }.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity.orZero(),
    posterPath = getOriginalImageUrl(posterPath.orEmpty()),
    releaseDate = releaseDate.orEmpty(),
    runtime = runtime.orZero(),
    voteAverage = voteAverage.orZero(),
    voteCount = voteCount.orZero()
)

internal fun GenreRemote.toDomain() = Genre(
    id = id.orNegativeDefault(),
    name = name.orEmpty()
)

internal fun ActorRemote.toDomain() = Actor(
    id = id.orNegativeDefault(),
    name = name.orEmpty(),
    profilePath = getOriginalImageUrl(profilePath.orEmpty()),
    character = character.orEmpty()
)