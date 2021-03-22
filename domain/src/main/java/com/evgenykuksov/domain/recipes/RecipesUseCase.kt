package com.evgenykuksov.domain.recipes

class RecipesUseCase(private val repository: RecipesRepository) {

    fun getRecipes() = repository.getRecipes()
}