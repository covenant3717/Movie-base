package com.evgenykuksov.recipes.screens.main

import com.evgenykuksov.domain.recipes.model.Recipe
import com.evgenykuksov.recipes.base.UiEffect
import com.evgenykuksov.recipes.base.UiIntent
import com.evgenykuksov.recipes.base.UiState

class MainContract {

    sealed class Intent : UiIntent {
        object Start : Intent()
    }

    sealed class State : UiState {
        object Idle : State()
        object Loading : State()
        data class Success(val list: List<Recipe>) : State()
    }

    sealed class Effect : UiEffect {
        data class ToastError(val message: String) : Effect()
    }
}