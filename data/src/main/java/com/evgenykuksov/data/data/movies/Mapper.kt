package com.evgenykuksov.data.data.movies

import com.evgenykuksov.core.extensions.orNegativeDefault
import com.evgenykuksov.core.extensions.orZero
import com.evgenykuksov.data.data.movies.remote.model.*
import com.evgenykuksov.data.data.movies.remote.model.ActorRemote
import com.evgenykuksov.data.data.movies.remote.model.GenreRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieDetailsRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieRemote
import com.evgenykuksov.data.util.TmdbImagePath
import com.evgenykuksov.data.util.YoutubeImagePath
import com.evgenykuksov.domain.movies.model.*

internal fun MovieRemote.toDomain() = Movie(
    id = id.orNegativeDefault(),
    backdropPath = TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, backdropPath.orEmpty()),
    genreIds = genreIds.orEmpty(),
    title = title.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity.orZero(),
    posterPath = TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, posterPath.orEmpty()),
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage.orZero(),
    voteCount = voteCount.orZero()
)

internal fun MovieDetailsRemote.toDomain() = MovieDetails(
    id = id.orNegativeDefault(),
    title = title.orEmpty(),
    backdropPath = TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, backdropPath.orEmpty()),
    budget = budget.orZero(),
    genres = genres?.map { it.toDomain() }.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity.orZero(),
    posterPath = TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, posterPath.orEmpty()),
    releaseDate = releaseDate.orEmpty(),
    runtime = runtime.orZero(),
    voteAverage = voteAverage.orZero(),
    voteCount = voteCount.orZero()
)

internal fun GenreRemote.toDomain() = Genre(
    id = id.orNegativeDefault(),
    name = name.orEmpty().replaceFirstChar { it.uppercase() }
)

internal fun ActorRemote.toDomain() = Actor(
    id = id.orNegativeDefault(),
    name = name.orEmpty(),
    profilePath = TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, profilePath.orEmpty()),
    character = character.orEmpty()
)

internal fun TrailerRemote.toDomain() = Trailer(
    id = id.orEmpty(),
    name = name.orEmpty(),
    key = key.orEmpty(),
    site = site.orEmpty(),
    official = official ?: false,
    backdropPath = YoutubeImagePath.getImagePath(key.orEmpty(), YoutubeImagePath.MQ_DEFAULT)
)

internal fun MovieImagesRemote.toDomain() = MovieImages(
    backdrops = backdrops?.map { TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, it.file_path.orEmpty()) }.orEmpty(),
    posters = posters?.map { TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, it.file_path.orEmpty()) }.orEmpty(),
)
