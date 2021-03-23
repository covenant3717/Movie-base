package com.evgenykuksov.recipes.screens.main

import android.util.Log
import com.evgenykuksov.domain.recipes.RecipesUseCase
import com.evgenykuksov.recipes.base.BaseViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainViewModel(private val recipesUseCase: RecipesUseCase, private val uiScheduler: Scheduler) : BaseViewModel() {

    fun getRecipes() {
        disposables += recipesUseCase.getRecipes()
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = {
                    Log.i("ml", "size: ${it.size}")
                },
                onError = {
                    Log.i("ml", "throwable: ${it.localizedMessage}")
                }
            )
    }
}