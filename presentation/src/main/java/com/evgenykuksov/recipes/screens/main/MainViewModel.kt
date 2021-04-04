package com.evgenykuksov.recipes.screens.main

import android.util.Log
import com.evgenykuksov.domain.recipes.RecipesUseCase
import com.evgenykuksov.recipes.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

class MainViewModel(private val recipesUseCase: RecipesUseCase) : BaseViewModel() {

    fun getRecipes() {
        launchOnUi {
            recipesUseCase.getRecipes()
                .onStart { Log.i("ml", "start") }
                .catch { exception ->
                    Log.i("ml", "exception: $exception")
                    exception.printStackTrace()
                }
                .collect {
                    Log.i("ml", "list: $it")
                }
        }
    }
}