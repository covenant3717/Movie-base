package com.evgenykuksov.data.data.actors

import com.evgenykuksov.core.extensions.isCyrillicLetters
import com.evgenykuksov.core.extensions.orNegativeDefault
import com.evgenykuksov.core.extensions.orZero
import com.evgenykuksov.data.data.actors.remote.model.ActorRemote
import com.evgenykuksov.data.util.TmdbImagePath
import com.evgenykuksov.domain.persons.model.Actor

internal fun ActorRemote.toDomain() = Actor(
    id = id.orNegativeDefault(),
    name = listNames?.find { it.isCyrillicLetters() } ?: name.orEmpty(),
    birthday = birthday.orEmpty(),
    placeOfBirth = placeOfBirth.orEmpty(),
    popularity = popularity.orZero(),
    biography = biography.orEmpty(),
    profilePhoto = TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, profilePhoto.orEmpty())
)