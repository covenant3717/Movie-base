package com.evgenykuksov.moviebase.screens.overview

import androidx.lifecycle.viewModelScope
import com.evgenykuksov.domain.recipes.RecipesUseCase
import com.evgenykuksov.domain.recipes.model.Recipe
import com.evgenykuksov.moviebase.base.BaseViewModel
import com.evgenykuksov.moviebase.screens.overview.items.MovieItem
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class OverviewViewModel(private val recipesUseCase: RecipesUseCase) :
    BaseViewModel<OverviewContract.Intent, OverviewContract.State, OverviewContract.SingleEvent>() {

    override fun createInitialState() = OverviewContract.State.Idle

    override fun handleIntent(intent: OverviewContract.Intent) {
        when (intent) {
            is OverviewContract.Intent.Start -> load()
        }
    }

    private fun load() = viewModelScope.launch {
        recipesUseCase.getRecipes()
            .onStart { setState(OverviewContract.State.Loading) }
            .catch { exception ->
                setState(OverviewContract.State.Idle)
                setSingleEvent(OverviewContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                setState(OverviewContract.State.Success(buildItems(it)))
            }
    }

    private fun buildItems(list: List<Recipe>): List<Item> =
        mutableListOf<MovieItem>()
            .apply {
                list.forEach { add(MovieItem(it)) }
            }
}