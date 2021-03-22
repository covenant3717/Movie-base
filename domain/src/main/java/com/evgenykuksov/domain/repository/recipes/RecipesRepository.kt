package com.evgenykuksov.domain.repository.recipes

import com.evgenykuksov.domain.repository.recipes.model.Recipe
import io.reactivex.rxjava3.core.Single

interface RecipesRepository {

    fun getRecipes(): Single<List<Recipe>>
}