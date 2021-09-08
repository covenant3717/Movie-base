package com.evgenykuksov.moviebase.screens.movie

import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.model.Actor
import com.evgenykuksov.domain.movies.model.FullMovieData
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseViewModel
import com.evgenykuksov.moviebase.common.commonitems.CustomEmptyItem
import com.evgenykuksov.moviebase.common.commonitems.CustomOneLIneLoadingItem
import com.evgenykuksov.moviebase.common.commonitems.ErrorItem
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

    override fun createInitialState() = MovieContract.State("stub", 0, null)

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
            .onStart { setState { copy(listItems = buildLoadingItems()) } }
            .catch { exception ->
                setState { copy(listItems = buildErrorItems()) }
                setSingleEvent(MovieContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                setState {
                    copy(
                        poster = it.movieDetails.posterPath,
                        delayUpdateItems = DELAY_UPDATING_ITEMS,
                        listItems = buildItems(it)
                    )
                }
            }
    }

    private fun buildLoadingItems(): List<Item> = listOf(
        CustomOneLIneLoadingItem(null, R.dimen.dimen_24, R.dimen.dimen_56, R.dimen.dimen_56),
        CustomEmptyItem(R.dimen.dimen_16),

        TitleItem(R.string.movie_item_title_rate),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomOneLIneLoadingItem(R.dimen.dimen_80, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_20),

        TitleItem(R.string.movie_item_title_description),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomOneLIneLoadingItem(null, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomOneLIneLoadingItem(null, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomOneLIneLoadingItem(null, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomOneLIneLoadingItem(R.dimen.dimen_100, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_20),

        TitleItem(R.string.movie_item_title_genre),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomOneLIneLoadingItem(null, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_20),

        TitleItem(R.string.movie_item_title_cast),
        CustomEmptyItem(R.dimen.dimen_8),
        CastItem(buildActorLoadingItems()),
        CustomEmptyItem(R.dimen.dimen_32)
    )

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

    private fun buildActorLoadingItems() = mutableListOf<Item>()
        .apply {
            for (i: Int in 1..7) {
                add(CustomEmptyItem(widthRes = R.dimen.dimen_20))
                add(ActorLoadingItem())
            }
            add(CustomEmptyItem(widthRes = R.dimen.dimen_20))
        }

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

private const val DELAY_UPDATING_ITEMS = 1000L