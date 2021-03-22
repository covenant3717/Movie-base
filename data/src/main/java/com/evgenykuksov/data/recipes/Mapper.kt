package com.evgenykuksov.data.recipes

import com.evgenykuksov.data.extensions.orNegativeDefault
import com.evgenykuksov.data.recipes.remote.model.RecipeRemote
import com.evgenykuksov.domain.recipes.model.Recipe

fun RecipeRemote.toDomain() = Recipe(
    id = id.orNegativeDefault(),
    title = title.orEmpty(),
    image = image.orEmpty()
)