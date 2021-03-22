package com.evgenykuksov.data.recipes.remote

import com.evgenykuksov.data.recipes.remote.api.RecipesApi

class RemoteRecipesImpl(private val api: RecipesApi) : RemoteRecipes {

    override fun getRecipes() = api.getRecipes()
}