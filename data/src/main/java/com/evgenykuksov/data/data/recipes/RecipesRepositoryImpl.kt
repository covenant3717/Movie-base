package com.evgenykuksov.data.data.recipes

import com.evgenykuksov.data.data.recipes.remote.RemoteRecipes
import com.evgenykuksov.domain.recipes.RecipesRepository
import com.evgenykuksov.domain.recipes.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class RecipesRepositoryImpl(
    private val remoteStore: RemoteRecipes
) : RecipesRepository {

    override fun getRecipes(): Flow<List<Recipe>> = remoteStore.getRecipes()
        .map { it.results?.map { it.toDomain() }.orEmpty() }
        .flowOn(Dispatchers.IO)
}