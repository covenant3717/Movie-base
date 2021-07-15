package com.evgenykuksov.domain.recipes

class RecipesUseCase(private val repository: MoviesRepository) {

    fun getRecipes() = repository.getRecipes()
}