package com.evgenykuksov.movie

import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.movies.MoviesUseCase
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.core.di.CoilModule
import com.evgenykuksov.core.extensions.addTo
import com.evgenykuksov.core.items.*
import com.evgenykuksov.domain.movies.model.*
import com.evgenykuksov.movie.items.*
import com.evgenykuksov.movie.items.RatingItem
import com.evgenykuksov.movie.navigation.MovieNavigation
import com.xwray.groupie.kotlinandroidextensions.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieViewModel @Inject constructor(
    private val navigator: MovieNavigation,
    stateHandle: SavedStateHandle,
    private val moviesUseCase: MoviesUseCase,
    @CoilModule.DefaultLoaderCoil private val defaultImageLoader: ImageLoader
) : BaseViewModel<MovieContract.Intent, MovieContract.State, MovieContract.SingleEvent>() {

    init {
        // https://stackoverflow.com/questions/65280323/how-to-pass-runtime-parameters-to-a-viewmodels-constructor-when-using-hilt-for
        // так можно передавать параметры во viewModel
        val movieId = stateHandle.get<Long>("arg_movie_id") ?: -1
        load(movieId)
    }

    override fun createInitialState() = MovieContract.State(null, null, null, null, null, null, null)

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
                        duration = it.details.runtime,
                        delayUpdateItems = DELAY_UPDATING_ITEMS,
                        listBackdrops = buildBackdropsItems(it.images.backdrops),
                        listItems = buildDetailsItems(it)
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

        // finance
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_finance),
        CustomEmptyItem(R.dimen.dimen_8),
        buildLoadingLineItem(),

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
                spaceBetweenItems = R.dimen.dimen_20,
                startEndSpacesRes = R.dimen.dimen_20
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
                spaceBetweenItems = R.dimen.dimen_8,
                startEndSpacesRes = R.dimen.dimen_20
            )
        ),

        // providers
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_providers),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomGroupItem(
            buildGroupLoadingItems(
                countItems = 4,
                itemWidthRes = R.dimen.dimen_40,
                itemHeightRes = R.dimen.dimen_40,
                itemCornerRoundRes = R.dimen.dimen_10,
                spaceBetweenItems = R.dimen.dimen_8,
                startEndSpacesRes = R.dimen.dimen_20
            )
        ),

        // actors
        CustomEmptyItem(R.dimen.dimen_20),
        buildTitleItem(R.string.item_title_cast),
        CustomEmptyItem(R.dimen.dimen_8),
        CustomGroupItem(
            buildGroupLoadingItems(
                countItems = 7,
                itemWidthRes = R.dimen.dimen_80,
                itemHeightRes = R.dimen.dimen_120,
                itemCornerRoundRes = R.dimen.dimen_14,
                spaceBetweenItems = R.dimen.dimen_20,
                startEndSpacesRes = R.dimen.dimen_20
            )
        ),

//        // workers
//        CustomEmptyItem(R.dimen.dimen_20),
//        buildTitleItem(R.string.item_title_workers),
//        CustomEmptyItem(R.dimen.dimen_8),
//        CustomGroupItem(
//            buildGroupLoadingItems(
//                countItems = 7,
//                itemWidthRes = R.dimen.dimen_80,
//                itemHeightRes = R.dimen.dimen_120,
//                itemCornerRoundRes = R.dimen.dimen_14,
//                spaceBetweenItems = R.dimen.dimen_20
//            )
//        ),

        CustomEmptyItem(R.dimen.dimen_32)
    )

    private fun buildBackdropsItems(listBackdrops: List<String>): List<Item> = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_4).addTo(this)
            listBackdrops.forEachIndexed { index, s ->
                BackdropItem(s, defaultImageLoader) { backdropPath ->
                    setState { copy(backdrop = backdropPath) }
                }.addTo(this)
                CustomEmptyItem(widthRes = R.dimen.dimen_4).addTo(this)
            }
        }
        .toList()

    private fun buildDetailsItems(data: MovieData): List<Item> = mutableListOf<Item>()
        .apply {
            // rate
            if (data.details.voteAverage == 0f && data.details.voteCount == 0) return@apply
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
            // finance
            if (data.details.revenue == 0 && data.details.budget == 0) return@apply
            CustomEmptyItem(R.dimen.dimen_20).addTo(this)
            buildTitleItem(R.string.item_title_finance).addTo(this)
            CustomEmptyItem(R.dimen.dimen_8).addTo(this)
            FinanceItem(data.details.revenue, data.details.budget).addTo(this)
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
            // providers
            if (data.providers.isEmpty()) return@apply
            CustomEmptyItem(R.dimen.dimen_20).addTo(this)
            buildTitleItem(R.string.item_title_providers).addTo(this)
            CustomEmptyItem(R.dimen.dimen_8).addTo(this)
            CustomGroupItem(buildProviderItems(data.providers)).addTo(this)
        }
        .apply {
            // actors
            if (data.credits.actors.isEmpty()) return@apply
            CustomEmptyItem(R.dimen.dimen_20).addTo(this)
            buildTitleItem(R.string.item_title_cast).addTo(this)
            CustomEmptyItem(R.dimen.dimen_8).addTo(this)
            CustomGroupItem(buildActorItems(data.credits.actors)).addTo(this)
        }
//        .apply {
//            // workers
//            if (data.credits.workers.isEmpty()) return@apply
//            CustomEmptyItem(R.dimen.dimen_20).addTo(this)
//            buildTitleItem(R.string.item_title_workers).addTo(this)
//            CustomEmptyItem(R.dimen.dimen_8).addTo(this)
//            CustomGroupItem(buildWorkerItems(data.credits.workers)).addTo(this)
//        }
        .apply { CustomEmptyItem(R.dimen.dimen_40).addTo(this) }

    private fun buildLoadingLineItem() = CustomLoadingItem(
        widthLayoutParams = ViewGroup.LayoutParams.MATCH_PARENT,
        heightRes = R.dimen.dimen_20,
        marginStartEndRes = R.dimen.dimen_20,
    )

    private fun buildTrailerItems(list: List<Trailer>) = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            list.forEach {
                TrailerItem(it, defaultImageLoader) { trailer ->
                    navigator.toYoutube(trailer.key)
                }.addTo(this)
                CustomEmptyItem(widthRes = R.dimen.dimen_16).addTo(this)
            }
        }
        .toList()

    private fun buildProviderItems(list: List<MovieProvider>) = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            list.forEach {
                ProviderItem(it, defaultImageLoader) { provider ->
                    // todo
                }.addTo(this)
                CustomEmptyItem(widthRes = R.dimen.dimen_8).addTo(this)
            }
        }
        .toList()

    private fun buildActorItems(list: List<Actor>) = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            list.forEach {
                ActorItem(it, defaultImageLoader) { extras ->
                    navigator.toActor(it.id, it.profilePath, extras)
                }.addTo(this)
                CustomEmptyItem(widthRes = R.dimen.dimen_16).addTo(this)
            }
        }
        .toList()

    private fun buildWorkerItems(list: List<Worker>) = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
            list.forEach {
                WorkerItem(it, defaultImageLoader) { extras ->
                    // todo
                }.addTo(this)
                CustomEmptyItem(widthRes = R.dimen.dimen_16).addTo(this)
            }
        }
        .toList()

    private fun buildTitleItem(@StringRes textRes: Int) = CustomTextItem(
        textContentRes = textRes,
        styleRes = R.style.TextAppearance_MaterialComponents_Subtitle1,
        colorRes = R.color.item_title,
        sideMarginsRes = R.dimen.dimen_20
    )

    companion object {

        private const val DELAY_UPDATING_ITEMS = 1000L
    }
}