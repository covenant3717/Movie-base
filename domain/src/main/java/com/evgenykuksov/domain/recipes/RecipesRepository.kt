package com.evgenykuksov.domain.recipes

import com.evgenykuksov.domain.recipes.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    fun getRecipes(): Flow<List<Recipe>>
}