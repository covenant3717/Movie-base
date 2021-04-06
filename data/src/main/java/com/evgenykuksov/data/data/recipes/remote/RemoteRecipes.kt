package com.evgenykuksov.data.data.recipes.remote

import com.evgenykuksov.data.data.recipes.remote.model.RecipeDataRemote
import kotlinx.coroutines.flow.Flow

interface RemoteRecipes {

    fun getRecipes(): Flow<RecipeDataRemote>
}