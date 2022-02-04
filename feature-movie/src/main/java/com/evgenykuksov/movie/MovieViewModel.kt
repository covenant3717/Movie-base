package com.evgenykuksov.movie

import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.domain.movies.model.Actor
import com.evgenykuksov.domain.movies.model.MovieData
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.core.extensions.addTo
import com.evgenykuksov.core.items.*
import com.evgenykuksov.domain.movies.model.Trailer
import com.evgenykuksov.movie.items.*
import com.evgenykuksov.movie.items.RatingItem
import com.evgenykuksov.movie.navigation.MovieNavigation
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.delay
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
                delay(5000)
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
        // rate
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_rate),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomLoadingItem(
            widthRes = R.dimen.dimen_120,
            heightRes = R.dimen.dimen_20,
            marginStartEndRes = R.dimen.dimen_20
        ),

        // description
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_description),
        CustomEmptyItem(R.dimen.dimen_8),
        buildLoadingLineItem(),
        CustomEmptyItem(R.dimen.dimen_8),
        buildLoadingLineItem(),
        CustomEmptyItem(R.dimen.dimen_8),
        buildLoadingLineItem(),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomLoadingItem(
            widthRes = R.dimen.dimen_100,
            heightRes = R.dimen.dimen_20,
            marginStartEndRes = R.dimen.dimen_20
        ),

        // trailers
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_trailers),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomGroupItem(
            buildGroupLoadingItems(
                countItems = 3,
                itemWidthRes = R.dimen.dimen_160,
                itemHeightRes = R.dimen.dimen_90,
                itemCornerRoundRes = R.dimen.dimen_14,
                spaceBetweenItems = R.dimen.dimen_20
            )
        ),

        // genre
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_genre),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomGroupItem(
            buildGroupLoadingItems(
                countItems = 3,
                itemWidthRes = R.dimen.dimen_100,
                itemHeightRes = R.dimen.dimen_30,
                itemCornerRoundRes = R.dimen.dimen_100,
                spaceBetweenItems = R.dimen.dimen_8
            )
        ),

        // cast
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_cast),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomGroupItem(
            buildGroupLoadingItems(
                countItems = 7,
                itemWidthRes = R.dimen.dimen_80,
                itemHeightRes = R.dimen.dimen_120,
                itemCornerRoundRes = R.dimen.dimen_14,
                spaceBetweenItems = R.dimen.dimen_20
            )
        ),
        CustomEmptyItem(R.dimen.dimen_32)
    )

    private fun buildErrorItems(): List<Item> = listOf<Item>(ErrorItem())

    private fun buildItems(data: MovieData): List<Item> = mutableListOf<Item>()
        .apply {
            // rate
            CustomEmptyItem(R.dimen.dimen_20).addTo(this)
            buildTitleItem(R.string.item_title_rate).addTo(this)
            CustomEmptyItem(R.dimen.dimen_8).addTo(this)
            RatingItem(data.details.voteAverage, data.details.voteCount).addTo(this)
        }
        .apply {
            // description
            if (data.details.overview.isBlank()) return@apply
            CustomEmptyItem(R.dimen.dimen_20).addTo(this)
            buildTitleItem(R.string.item_title_description).addTo(this)
            CustomEmptyItem(R.dimen.dimen_8).addTo(this)
            DescriptionItem(data.details.overview).addTo(this)
        }
        .apply {
            // trailers
            if (data.trailers.isEmpty()) return@apply
            CustomEmptyItem(R.dimen.dimen_20).addTo(this)
            buildTitleItem(R.string.item_title_trailers).addTo(this)
            CustomEmptyItem(R.dimen.dimen_8).addTo(this)
            CustomGroupItem(buildTrailerItems(data.trailers)).addTo(this)
        }
        .apply {
            // genre
            if (data.details.genres.isEmpty()) return@apply
            CustomEmptyItem(R.dimen.dimen_20).addTo(this)
            buildTitleItem(R.string.item_title_genre).addTo(this)
            CustomEmptyItem(R.dimen.dimen_8).addTo(this)
            GenreItem(data.details.genres).addTo(this)
        }
        .apply {
            // cast
            if (data.cast.isEmpty()) return@apply
            CustomEmptyItem(R.dimen.dimen_20).addTo(this)
            buildTitleItem(R.string.item_title_cast).addTo(this)
            CustomEmptyItem(R.dimen.dimen_8).addTo(this)
            CustomGroupItem(buildActorItems(data.cast)).addTo(this)
            CustomEmptyItem(R.dimen.dimen_32).addTo(this)
        }

    private fun buildLoadingLineItem() = CustomLoadingItem(
        widthLayoutParams = ViewGroup.LayoutParams.MATCH_PARENT,
        heightRes = R.dimen.dimen_20,
        marginStartEndRes = R.dimen.dimen_20,
    )

    private fun buildTrailerItems(listActor: List<Trailer>) = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            listActor.forEach {
                if (it.official) {
                    TrailerItem(it, defaultImageLoader) { trailer -> navigator.toYoutube(trailer.key) }.addTo(this)
                    CustomEmptyItem(widthRes = R.dimen.dimen_16).addTo(this)
                }
            }
        }
        .toList()

    private fun buildActorItems(listActor: List<Actor>) = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            listActor.forEach {
                ActorItem(it, defaultImageLoader) { actor, extras ->
                    navigator.toActor(actor.id, actor.profilePath, extras)
                }.addTo(this)
                CustomEmptyItem(widthRes = R.dimen.dimen_16).addTo(this)
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