package com.evgenykuksov.domain.recipes

import com.evgenykuksov.domain.recipes.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getRecipes(): Flow<List<Movie>>
}