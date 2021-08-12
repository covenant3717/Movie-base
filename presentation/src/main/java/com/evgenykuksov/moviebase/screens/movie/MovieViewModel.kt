package com.evgenykuksov.moviebase.screens.movie

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.moviebase.base.BaseViewModel
import com.evgenykuksov.moviebase.screens.overview.OverviewContract
import com.evgenykuksov.moviebase.screens.overview.items.MovieDividerItem
import com.evgenykuksov.moviebase.screens.overview.items.MovieErrorItem
import com.evgenykuksov.moviebase.screens.overview.items.MovieItem
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieViewModel(
    private val moviesUseCase: MoviesUseCase
) :
    BaseViewModel<MovieContract.Intent, MovieContract.State, MovieContract.SingleEvent>() {

    override fun createInitialState() = MovieContract.State(null)

    override fun handleIntent(intent: MovieContract.Intent) {
        when (intent) {
            is MovieContract.Intent.LoadMovieDetails -> load(intent.movieId)
        }
    }

    private fun load(movieId: Long) = viewModelScope.launch {
        moviesUseCase.getMovieDetails(movieId)
            .onStart {
//                setState { copy(listItems = buildLoadingItems()) }
            }
            .catch { exception ->
                setState { copy(listItems = buildErrorItems()) }
                setSingleEvent(MovieContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {

            }
    }

//    private fun buildLoadingItems(): List<Item> = listOf<Item>(MovieLoadingItem(gifLoader))

    private fun buildErrorItems(): List<Item> = listOf<Item>(MovieErrorItem())

    private fun buildItems(list: List<Movie>): List<Item> = mutableListOf<Item>()
        .apply {
            add(MovieDividerItem())
            list.forEach {
                add(MovieItem(it))
                add(MovieDividerItem())
            }
        }
}