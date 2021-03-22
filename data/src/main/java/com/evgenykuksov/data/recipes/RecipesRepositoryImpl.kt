package com.evgenykuksov.data.recipes

import com.evgenykuksov.data.recipes.remote.RemoteRecipes
import com.evgenykuksov.domain.repository.recipes.RecipesRepository
import com.evgenykuksov.domain.repository.recipes.model.Recipe
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class RecipesRepositoryImpl(
    private val remoteStore: RemoteRecipes,
    private val ioScheduler: Scheduler
) : RecipesRepository {

    override fun getRecipes(): Single<List<Recipe>> = remoteStore.getRecipes()
        .map { it.map { it.toDomain() } }
        .subscribeOn(ioScheduler)
}