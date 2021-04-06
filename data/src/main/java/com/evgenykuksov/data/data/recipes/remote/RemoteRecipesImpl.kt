package com.evgenykuksov.data.data.recipes.remote

import com.evgenykuksov.data.data.recipes.remote.api.RecipesApi
import kotlinx.coroutines.flow.flow

class RemoteRecipesImpl(private val api: RecipesApi) : RemoteRecipes {

    override fun getRecipes() = flow { emit(api.getRecipes()) }
}