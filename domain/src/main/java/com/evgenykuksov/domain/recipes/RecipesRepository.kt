package com.evgenykuksov.domain.recipes

import com.evgenykuksov.domain.recipes.model.Recipe
import io.reactivex.rxjava3.core.Single

interface RecipesRepository {

    fun getRecipes(): Single<List<Recipe>>
}