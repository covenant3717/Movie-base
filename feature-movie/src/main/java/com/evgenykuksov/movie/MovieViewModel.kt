package com.evgenykuksov.movie

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.model.Actor
import com.evgenykuksov.domain.movies.model.MovieData
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.core.items.*
import com.evgenykuksov.movie.items.*
import com.evgenykuksov.movie.items.ActorLoadingItem
import com.evgenykuksov.movie.items.CastItem
import com.evgenykuksov.movie.items.GenreItem
import com.evgenykuksov.movie.items.RatingItem
import com.evgenykuksov.movie.navigation.MovieNavigation
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieId: Long,
    private val navigator: MovieNavigation,
    private val moviesUseCase: MoviesUseCase,
    private val defaultImageLoader: ImageLoader
) : BaseViewModel<MovieContract.Intent, MovieContract.State, MovieContract.SingleEvent>() {

    init {
        load(movieId)
    }

    override fun createInitialState() = MovieContract.State(null, "", "", 0, null)

    override fun handleIntent(intent: MovieContract.Intent) {
        when (intent) {
            is MovieContract.Intent.Back -> navigator.back()
        }
    }

    private fun load(movieId: Long) = viewModelScope.launch {
        moviesUseCase.getMovieData(movieId)
            .onStart { setState { copy(listItems = buildLoadingItems()) } }
            .catch { exception ->
                setState { copy(listItems = buildErrorItems()) }
                setSingleEvent(MovieContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                setState {
                    copy(
                        backdrop = it.details.backdropPath,
                        name = it.details.title,
                        date = it.details.releaseDate,
                        delayUpdateItems = DELAY_UPDATING_ITEMS,
                        listItems = buildItems(it)
                    )
                }
            }
    }

    private fun buildLoadingItems(): List<Item> = listOf(
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_rate),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomSingleLineLoadingItem(R.dimen.dimen_80, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_20),

        buildTitleItem(R.string.item_title_genre),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomSingleLineLoadingItem(null, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_20),

        buildTitleItem(R.string.item_title_description),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomSingleLineLoadingItem(null, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomSingleLineLoadingItem(null, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomSingleLineLoadingItem(null, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomSingleLineLoadingItem(R.dimen.dimen_100, R.dimen.dimen_20, R.dimen.dimen_20, R.dimen.dimen_20),
        CustomEmptyItem(R.dimen.dimen_20),

        buildTitleItem(R.string.item_title_cast),
        CustomEmptyItem(R.dimen.dimen_8),
        CastItem(buildActorLoadingItems()),
        CustomEmptyItem(R.dimen.dimen_32)
    )

    private fun buildErrorItems(): List<Item> = listOf<Item>(ErrorItem())

    private fun buildItems(data: MovieData): List<Item> = listOf(
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_rate),
        CustomEmptyItem(R.dimen.dimen_8),
        RatingItem(data.details.voteAverage, data.details.voteCount),
        CustomEmptyItem(R.dimen.dimen_20),

        buildTitleItem(R.string.item_title_genre),
        CustomEmptyItem(R.dimen.dimen_8),
        GenreItem(data.details.genres),
        CustomEmptyItem(R.dimen.dimen_20),

        buildTitleItem(R.string.item_title_description),
        CustomEmptyItem(R.dimen.dimen_8),
        DescriptionItem(data.details.overview),
        CustomEmptyItem(R.dimen.dimen_20),

        buildTitleItem(R.string.item_title_cast),
        CustomEmptyItem(R.dimen.dimen_8),
        CastItem(buildActorItems(data.cast)),
        CustomEmptyItem(R.dimen.dimen_32),
    )

    private fun buildActorLoadingItems() = mutableListOf<Item>()
        .apply {
            for (i in 1..7) {
                add(CustomEmptyItem(widthRes = R.dimen.dimen_20))
                add(ActorLoadingItem())
            }
            add(CustomEmptyItem(widthRes = R.dimen.dimen_20))
        }

    private fun buildActorItems(listActor: List<Actor>) = mutableListOf<Item>()
        .apply {
            add(CustomEmptyItem(widthRes = R.dimen.dimen_20))
            listActor.forEach {
                add(
                    ActorItem(it, defaultImageLoader) { actor, extras ->
                        navigator.toActor(actor.id, actor.profilePath, extras)
                    }
                )
                add(CustomEmptyItem(widthRes = R.dimen.dimen_16))
            }
        }
        .toList()

    private fun buildTitleItem(@StringRes textRes: Int) = CustomTextItem(
        textContentRes = textRes,
        styleRes = R.style.TextAppearance_MaterialComponents_Subtitle1,
        colorRes = R.color.item_title,
        startPaddingRes = R.dimen.dimen_20,
        endPaddingRes = R.dimen.dimen_20,
    )

    companion object {

        private const val DELAY_UPDATING_ITEMS = 1000L
    }
}