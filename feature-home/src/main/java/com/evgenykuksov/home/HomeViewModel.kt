package com.evgenykuksov.home

import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.domain.movies.model.MoviesCategory
import com.evgenykuksov.domain.movies.model.MoviesData
import com.evgenykuksov.domain.profile.ProfileUseCase
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.core.extensions.addTo
import com.evgenykuksov.core.items.CustomEmptyItem
import com.evgenykuksov.core.items.ErrorItem
import com.evgenykuksov.home.items.MovieItem
import com.evgenykuksov.home.items.MovieLoadingItem
import com.evgenykuksov.domain.movies.model.MoviesGrouping
import com.evgenykuksov.home.navigation.HomeNavigation
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val navigator: HomeNavigation,
    private val moviesUseCase: MoviesUseCase,
    private val profileUseCase: ProfileUseCase,
    private val defaultImageLoader: ImageLoader,
    private val gifLoader: ImageLoader
) : BaseViewModel<HomeContract.Intent, HomeContract.State, HomeContract.SingleEvent>() {

    private var moviesData: MoviesData? = null

    init {
        load()
    }

    override fun createInitialState() = HomeContract.State(MoviesGrouping.Linear, MoviesCategory.UPCOMING, null, null)

    override fun handleIntent(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.ChangeGrouping -> handleChangeGrouping(intent.grouping)
            is HomeContract.Intent.SelectCategory -> handleSelectCategory(intent.category)
        }
    }

    private fun load() {
        viewModelScope.launch {
            moviesUseCase.getMovies()
                .onStart { setState { copy(listItems = buildLoadingItems()) } }
                .catch { exception ->
                    setState { copy(listItems = buildErrorItems()) }
                    setSingleEvent(HomeContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
                }
                .collect {
                    moviesData = it
                    setState { copy(listItems = buildItems(getMoviesByCategory(state.value.category))) }
                }
        }

        viewModelScope.launch {
            profileUseCase.getRating()
                .catch { exception -> setSingleEvent(HomeContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty())) }
                .collect { setState { copy(rating = it) } }
        }
    }

    private fun buildLoadingItems(): List<Item> = listOf<Item>(MovieLoadingItem(gifLoader))

    private fun buildErrorItems(): List<Item> = listOf<Item>(ErrorItem())

    private fun buildItems(list: List<Movie>, grouping: MoviesGrouping = state.value.grouping): List<Item> =
        mutableListOf<Item>()
            .apply {
                when (grouping) {
                    MoviesGrouping.Linear -> addAll(buildMoviesLinearItems(list))
                    MoviesGrouping.Grid -> addAll(buildMoviesGridItems(list))
                }
            }

    private fun buildMoviesLinearItems(list: List<Movie>): List<Item> = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            list.forEach {
                MovieItem(it, defaultImageLoader, R.dimen.dimen_20) { movie, extras ->
                    navigator.toMovie(movie.id, movie.posterPath, extras)
                }.addTo(this)
                CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            }
        }

    private fun buildMoviesGridItems(list: List<Movie>): List<Item> = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            list.forEach {
                MovieItem(it, defaultImageLoader, null) { movie, extras ->
                    navigator.toMovie(movie.id, movie.posterPath, extras)
                }.addTo(this)
            }
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
        }

    private fun handleChangeGrouping(grouping: MoviesGrouping) = moviesData?.let {
        val movieItems = buildItems(getMoviesByCategory(state.value.category), grouping)
        setState { copy(grouping = grouping, listItems = movieItems) }
    }

    private fun handleSelectCategory(category: MoviesCategory) = moviesData?.let {
        val movieItems = buildItems(getMoviesByCategory(category))
        setState { copy(category = category, listItems = movieItems) }
    }

    private fun getMoviesByCategory(category: MoviesCategory): List<Movie> = moviesData?.let {
        when (category) {
            MoviesCategory.UPCOMING -> it.listUpcoming
            MoviesCategory.NOW_PLAYING -> it.listNowPlaying
            MoviesCategory.POPULAR -> it.listPopular
            MoviesCategory.TOP_RATED -> it.listTopRated
        }
    } ?: emptyList()
}