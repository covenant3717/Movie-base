package com.evgenykuksov.data.data.persons

import com.evgenykuksov.core.extensions.isCyrillicLetters
import com.evgenykuksov.core.extensions.orNegativeDefault
import com.evgenykuksov.core.extensions.orZero
import com.evgenykuksov.data.data.persons.remote.model.ActorInfoRemote
import com.evgenykuksov.data.data.persons.remote.model.PersonExternalIdsRemote
import com.evgenykuksov.data.util.TmdbImagePath
import com.evgenykuksov.domain.persons.model.ActorInfo
import com.evgenykuksov.domain.persons.model.PersonExternalIds

internal fun ActorInfoRemote.toDomain() = ActorInfo(
    id = id.orNegativeDefault(),
    name = listNames?.find { it.isCyrillicLetters() } ?: name.orEmpty(),
    birthday = birthday.orEmpty(),
    placeOfBirth = placeOfBirth.orEmpty(),
    popularity = popularity.orZero(),
    biography = biography.orEmpty(),
    profilePhoto = TmdbImagePath.getImagePath(TmdbImagePath.ORIGINAL, profilePhoto.orEmpty())
)

internal fun PersonExternalIdsRemote.toDomain() = PersonExternalIds(
    id = id.orNegativeDefault(),
    imdbId = imdbId.orEmpty(),
    tvrageId = tvrageId.orNegativeDefault(),
    facebookId = facebookId.orEmpty(),
    instagramId = instagramId.orEmpty(),
    twitterId = twitterId.orEmpty(),
)
