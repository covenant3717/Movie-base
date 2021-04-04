package com.evgenykuksov.data.recipes.remote

import com.evgenykuksov.data.recipes.remote.model.RecipeListRemote
import kotlinx.coroutines.flow.Flow

interface RemoteRecipes {

    fun getRecipes(): Flow<RecipeListRemote>
}