package com.evgenykuksov.moviebase.screens.overview

import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.moviebase.base.BaseViewModel
import com.evgenykuksov.moviebase.screens.overview.items.MovieDividerItem
import com.evgenykuksov.moviebase.screens.overview.items.MovieErrorItem
import com.evgenykuksov.moviebase.screens.overview.items.MovieItem
import com.evgenykuksov.moviebase.screens.overview.items.MovieLoadingItem
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class OverviewViewModel(
    private val moviesUseCase: MoviesUseCase,
    private val gifLoader: ImageLoader
) :
    BaseViewModel<OverviewContract.Intent, OverviewContract.State, OverviewContract.SingleEvent>() {

    override fun createInitialState() = OverviewContract.State.Idle

    override fun handleIntent(intent: OverviewContract.Intent) {
        when (intent) {
            is OverviewContract.Intent.Start -> load()
        }
    }

    private fun load() = viewModelScope.launch {
        moviesUseCase.getNowPlaying()
            .onStart { setState(OverviewContract.State.Loading(buildLoadingItems())) }
            .catch { exception ->
                setState(OverviewContract.State.Error(buildErrorItems()))
                setSingleEvent(OverviewContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                setState(OverviewContract.State.Success(buildItems(it)))
            }
    }

    private fun buildLoadingItems(): List<Item> = listOf<Item>(MovieLoadingItem(gifLoader))

    private fun buildItems(list: List<Movie>): List<Item> = mutableListOf<Item>()
        .apply {
            add(MovieDividerItem())
            list.forEach {
                add(MovieItem(it))
                add(MovieDividerItem())
            }
        }

    private fun buildErrorItems(): List<Item> = listOf<Item>(MovieErrorItem())
}