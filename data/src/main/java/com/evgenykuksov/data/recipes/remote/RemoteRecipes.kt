package com.evgenykuksov.data.recipes.remote

import com.evgenykuksov.data.recipes.remote.model.RecipeRemote
import io.reactivex.rxjava3.core.Single

interface RemoteRecipes {

    fun getRecipes(): Single<List<RecipeRemote>>
}