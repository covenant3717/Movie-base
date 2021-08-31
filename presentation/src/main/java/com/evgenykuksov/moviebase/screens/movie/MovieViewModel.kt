package com.evgenykuksov.moviebase.screens.movie

import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.model.MovieDetails
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseViewModel
import com.evgenykuksov.moviebase.commonitems.CustomEmptyItem
import com.evgenykuksov.moviebase.screens.movie.items.*
import com.evgenykuksov.moviebase.screens.movie.items.DescriptionItem
import com.evgenykuksov.moviebase.screens.movie.items.GenreItem
import com.evgenykuksov.moviebase.screens.movie.items.NameItem
import com.evgenykuksov.moviebase.screens.movie.items.PosterItem
import com.evgenykuksov.moviebase.screens.movie.items.RatingItem
import com.evgenykuksov.moviebase.screens.movie.items.TitleItem
import com.evgenykuksov.moviebase.screens.overview.items.MovieErrorItem
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieViewModel(
    private val moviesUseCase: MoviesUseCase,
    private val defaultImageLoader: ImageLoader
) :
    BaseViewModel<MovieContract.Intent, MovieContract.State, MovieContract.SingleEvent>() {

    override fun createInitialState() = MovieContract.State(null)

    override fun handleIntent(intent: MovieContract.Intent) {
        when (intent) {
            is MovieContract.Intent.LoadMovieDetails -> load(intent.movieId)
        }
    }

    private fun load(movieId: Long) {
        viewModelScope.launch {
            moviesUseCase.getMovieDetails(movieId)
                .onStart {
//                setState { copy(listItems = buildLoadingItems()) }
                }
                .catch { exception ->
                    setState { copy(listItems = buildErrorItems()) }
                    setSingleEvent(MovieContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
                }
                .collect {
                    setState { copy(listItems = buildItems(it)) }
                }
        }

        viewModelScope.launch {
            moviesUseCase.getCast(movieId)
                .onStart {
//                setState { copy(listItems = buildLoadingItems()) }
                }
                .catch { exception ->
                    setState { copy(listItems = buildErrorItems()) }
                    setSingleEvent(MovieContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
                }
                .collect {
//                    setState { copy(listItems = buildItems(it)) }
                }
        }
    }

//    private fun buildLoadingItems(): List<Item> = listOf<Item>(MovieLoadingItem(gifLoader))

    private fun buildErrorItems(): List<Item> = listOf<Item>(MovieErrorItem())

    private fun buildItems(movieDetails: MovieDetails): List<Item> = listOf(
        PosterItem(movieDetails, defaultImageLoader),
        CustomEmptyItem(R.dimen.dimen_16),
        NameItem(movieDetails.title),
        CustomEmptyItem(R.dimen.dimen_16),

        TitleItem(R.string.movie_item_title_rate),
        CustomEmptyItem(R.dimen.dimen_8),
        RatingItem(movieDetails.voteAverage, movieDetails.voteCount),
        CustomEmptyItem(R.dimen.dimen_20),

        TitleItem(R.string.movie_item_title_description),
        CustomEmptyItem(R.dimen.dimen_8),
        DescriptionItem(movieDetails.overview),
        CustomEmptyItem(R.dimen.dimen_20),

        TitleItem(R.string.movie_item_title_genre),
        CustomEmptyItem(R.dimen.dimen_8),
        GenreItem(movieDetails.genres),
        CustomEmptyItem(R.dimen.dimen_20),

        TitleItem(R.string.movie_item_title_cast),
        CustomEmptyItem(R.dimen.dimen_8),
    )
}