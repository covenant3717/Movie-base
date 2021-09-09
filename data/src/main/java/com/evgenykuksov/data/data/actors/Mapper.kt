package com.evgenykuksov.data.data.actors

import com.evgenykuksov.data.data.actors.remote.model.ActorInfoRemote
import com.evgenykuksov.data.extensions.orNegativeDefault
import com.evgenykuksov.data.extensions.orZero
import com.evgenykuksov.data.util.getOriginalImageUrl
import com.evgenykuksov.domain.actors.model.ActorInfo

internal fun ActorInfoRemote.toDomain() = ActorInfo(
    id = id.orNegativeDefault(),
    name = name.orEmpty(),
    birthday = birthday.orEmpty(),
    placeOfBirth = placeOfBirth.orEmpty(),
    popularity = popularity.orZero(),
    biography = biography.orEmpty(),
    profilePhoto = getOriginalImageUrl(profilePhoto.orEmpty())
)