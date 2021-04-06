package com.evgenykuksov.recipes.screens.main

import androidx.lifecycle.viewModelScope
import com.evgenykuksov.domain.recipes.RecipesUseCase
import com.evgenykuksov.recipes.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val recipesUseCase: RecipesUseCase) :
    BaseViewModel<MainContract.Intent, MainContract.State, MainContract.SingleEvent>() {

    override fun createInitialState() = MainContract.State.Idle

    override fun handleIntent(intent: MainContract.Intent) {
        when (intent) {
            is MainContract.Intent.Start -> load()
        }
    }

    private fun load() = viewModelScope.launch {
        recipesUseCase.getRecipes()
            .onStart { setState(MainContract.State.Loading) }
            .catch { exception ->
                setState(MainContract.State.Idle)
                setSingleEvent(MainContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
                exception.printStackTrace()
            }
            .collect {
                setState(MainContract.State.Success(it))
            }
    }
}