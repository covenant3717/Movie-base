package com.evgenykuksov.data.data.actors

import com.evgenykuksov.core.extensions.isCyrillicLetters
import com.evgenykuksov.core.extensions.orNegativeDefault
import com.evgenykuksov.core.extensions.orZero
import com.evgenykuksov.data.data.actors.remote.model.ActorInfoRemote
import com.evgenykuksov.data.util.TmdbImagePath
import com.evgenykuksov.domain.actors.model.ActorInfo

internal fun ActorInfoRemote.toDomain() = ActorInfo(
    id = id.orNegativeDefault(),
    name = listNames?.find { it.isCyrillicLetters() } ?: name.orEmpty(),
    birthday = birthday.orEmpty(),
    placeOfBirth = placeOfBirth.orEmpty(),
    popularity = popularity.orZero(),
    biography = biography.orEmpty(),
    profilePhoto = TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, profilePhoto.orEmpty())
)