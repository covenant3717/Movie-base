package com.evgenykuksov.moviebase.screens.overview

import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.domain.movies.model.MoviesCategory
import com.evgenykuksov.domain.movies.model.MoviesData
import com.evgenykuksov.domain.profile.ProfileUseCase
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.moviebase.common.commonitems.CustomEmptyItem
import com.evgenykuksov.moviebase.common.commonitems.ErrorItem
import com.evgenykuksov.moviebase.screens.overview.items.MovieItem
import com.evgenykuksov.moviebase.screens.overview.items.MovieLoadingItem
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class OverviewViewModel(
    private val moviesUseCase: MoviesUseCase,
    private val profileUseCase: ProfileUseCase,
    private val defaultImageLoader: ImageLoader,
    private val gifLoader: ImageLoader
) :
    BaseViewModel<OverviewContract.Intent, OverviewContract.State, OverviewContract.SingleEvent>() {

    private var moviesData: MoviesData? = null

    override fun createInitialState() = OverviewContract.State(null, null)

    override fun handleIntent(intent: OverviewContract.Intent) {
        when (intent) {
            is OverviewContract.Intent.Start -> load()
            is OverviewContract.Intent.SelectCategory -> handleSelectCategory(intent.category)
        }
    }

    private fun load() {
        viewModelScope.launch {
            moviesUseCase.getAll()
                .onStart { setState { copy(listItems = buildLoadingItems()) } }
                .catch { exception ->
                    setState { copy(listItems = buildErrorItems()) }
                    setSingleEvent(OverviewContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
                }
                .collect {
                    moviesData = it
                    setState { copy(listItems = buildItems(it.listNowPlaying)) }
                }
        }

        viewModelScope.launch {
            profileUseCase.getRating()
                .catch { exception -> setSingleEvent(OverviewContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty())) }
                .collect { setState { copy(rating = it) } }
        }
    }

    private fun buildLoadingItems(): List<Item> = listOf<Item>(MovieLoadingItem(gifLoader))

    private fun buildErrorItems(): List<Item> = listOf<Item>(ErrorItem())

    private fun buildItems(list: List<Movie>): List<Item> = mutableListOf<Item>()
        .apply {
            add(CustomEmptyItem(widthRes = R.dimen.dimen_20))
            list.forEach {
                add(
                    MovieItem(it, defaultImageLoader) {
                        setSingleEvent(OverviewContract.SingleEvent.StartMovieActivity(it))
                    }
                )
                add(CustomEmptyItem(widthRes = R.dimen.dimen_20))
            }
        }

    private fun handleSelectCategory(category: MoviesCategory) =
        moviesData?.let {
            when (category) {
                MoviesCategory.New -> setState { copy(listItems = buildItems(it.listNowPlaying)) }
                MoviesCategory.Popular -> setState { copy(listItems = buildItems(it.listPopular)) }
                MoviesCategory.TopRated -> setState { copy(listItems = buildItems(it.listTopRated)) }
            }
        }
}