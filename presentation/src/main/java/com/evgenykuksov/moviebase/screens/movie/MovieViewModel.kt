package com.evgenykuksov.moviebase.screens.movie

import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.model.Actor
import com.evgenykuksov.domain.movies.model.FullMovieData
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseViewModel
import com.evgenykuksov.moviebase.commonitems.CustomEmptyItem
import com.evgenykuksov.moviebase.commonitems.ErrorItem
import com.evgenykuksov.moviebase.screens.movie.items.*
import com.evgenykuksov.moviebase.screens.movie.items.CastItem
import com.evgenykuksov.moviebase.screens.movie.items.DescriptionItem
import com.evgenykuksov.moviebase.screens.movie.items.GenreItem
import com.evgenykuksov.moviebase.screens.movie.items.NameItem
import com.evgenykuksov.moviebase.screens.movie.items.RatingItem
import com.evgenykuksov.moviebase.screens.movie.items.TitleItem
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    private val moviesUseCase: MoviesUseCase,
    private val defaultImageLoader: ImageLoader
) : BaseViewModel<MovieContract.Intent, MovieContract.State, MovieContract.SingleEvent>() {

    override fun createInitialState() = MovieContract.State(null, null)

    override fun handleIntent(intent: MovieContract.Intent) {
        when (intent) {
            is MovieContract.Intent.LoadMovieDetails -> load(intent.movieId)
        }
    }

    private fun load(movieId: Long) = viewModelScope.launch {
        combine(
            moviesUseCase.getMovieDetails(movieId),
            moviesUseCase.getCast(movieId)
        ) { movieDetails, cast -> FullMovieData(movieDetails, cast) }
            .onStart {
//                setState { copy(listItems = buildErrorItems()) }
            }
            .catch { exception ->
                setState { copy(listItems = buildErrorItems()) }
                setSingleEvent(MovieContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                setState { copy(poster = it.movieDetails.posterPath, listItems = buildItems(it)) }
            }
    }

//    private fun buildLoadingItems(): List<Item> = listOf<Item>(MovieLoadingItem(gifLoader))

    private fun buildErrorItems(): List<Item> = listOf<Item>(ErrorItem())

    private fun buildItems(data: FullMovieData): List<Item> = listOf(
        NameItem(data.movieDetails.title),
        CustomEmptyItem(R.dimen.dimen_16),

        TitleItem(R.string.movie_item_title_rate),
        CustomEmptyItem(R.dimen.dimen_8),
        RatingItem(data.movieDetails.voteAverage, data.movieDetails.voteCount),
        CustomEmptyItem(R.dimen.dimen_20),

        TitleItem(R.string.movie_item_title_description),
        CustomEmptyItem(R.dimen.dimen_8),
        DescriptionItem(data.movieDetails.overview),
        CustomEmptyItem(R.dimen.dimen_20),

        TitleItem(R.string.movie_item_title_genre),
        CustomEmptyItem(R.dimen.dimen_8),
        GenreItem(data.movieDetails.genres),
        CustomEmptyItem(R.dimen.dimen_20),

        TitleItem(R.string.movie_item_title_cast),
        CustomEmptyItem(R.dimen.dimen_8),
        CastItem(buildActorItems(data.listActor)),
        CustomEmptyItem(R.dimen.dimen_32),
    )

    private fun buildActorItems(listActor: List<Actor>) = mutableListOf<Item>()
        .apply {
            add(CustomEmptyItem(widthRes = R.dimen.dimen_20))
            listActor.forEach {
                add(ActorItem(it, defaultImageLoader) {})
                add(CustomEmptyItem(widthRes = R.dimen.dimen_16))
            }
        }
        .toList()
}