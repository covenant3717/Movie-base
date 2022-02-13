package com.evgenykuksov.actor

import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.actor.items.ActorImageItem
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.core.extensions.addTo
import com.evgenykuksov.core.items.CustomEmptyItem
import com.evgenykuksov.core.items.ErrorItem
import com.evgenykuksov.core.items.buildGroupLoadingItems
import com.evgenykuksov.domain.persons.PersonsUseCase
import com.evgenykuksov.domain.persons.model.ActorData
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ActorViewModel(
    private val actorId: Long,
    private val personsUseCase: PersonsUseCase,
    private val defaultImageLoader: ImageLoader
) : BaseViewModel<ActorContract.Intent, ActorContract.State, ActorContract.SingleEvent>() {

    private var actorData: ActorData? = null

    init {
        load(actorId)
    }

    override fun createInitialState() = ActorContract.State(null, null, null)

    override fun handleIntent(intent: ActorContract.Intent) {
        when (intent) {
            is ActorContract.Intent.TouchedBtnInfo -> handleTouchBtnInfo()
        }
    }

    private fun load(actorId: Long) = viewModelScope.launch {
        personsUseCase.getActorData(actorId)
            .onStart { setState { copy(listItems = buildLoadingItems()) } }
            .catch { exception ->
                setSingleEvent(ActorContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                actorData = it
                setState {
                    copy(
                        listItems = buildItems(it.images),
                        delayUpdateItems = 1000
                    )
                }
            }
    }

    private fun buildLoadingItems() = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_16).addTo(this)
            ActorImageItem(imagePathRes = R.drawable.ic_info, imageLoader = defaultImageLoader) {}.addTo(this)
        }
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_10).addTo(this)
            addAll(
                buildGroupLoadingItems(
                    countItems = 10,
                    itemWidthRes = R.dimen.dimen_60,
                    itemHeightRes = R.dimen.dimen_80,
                    itemCornerRoundRes = R.dimen.dimen_10,
                    spaceBetweenItems = R.dimen.dimen_10,
                    startEndSpacesRes = null
                )
            )
            CustomEmptyItem(widthRes = R.dimen.dimen_20).addTo(this)
        }

    private fun buildItems(images: List<String>) = mutableListOf<Item>()
        .apply {
            CustomEmptyItem(widthRes = R.dimen.dimen_16).addTo(this)
            ActorImageItem(imagePathRes = R.drawable.ic_info, imageLoader = defaultImageLoader) {}.addTo(this)
        }
        .apply {
            images.forEachIndexed { index, s ->
                CustomEmptyItem(widthRes = R.dimen.dimen_10).addTo(this)
                ActorImageItem(imagePath = s, imageLoader = defaultImageLoader) {
                    setState { copy(photo = it) }
                }.addTo(this)
            }
            CustomEmptyItem(widthRes = R.dimen.dimen_16).addTo(this)
        }

//    private fun buildItems(actorInfo: ActorInfo): List<Item> = listOf(
//        CustomTextItem(
//            textContent = actorInfo.name,
//            styleRes = R.style.TextAppearance_MaterialComponents_Headline6,
//            colorRes = R.color.dialog_actor_name,
//            startPaddingRes = R.dimen.dimen_20,
//            endPaddingRes = R.dimen.dimen_20,
//            gravityState = Gravity.CENTER_HORIZONTAL
//        ),
//        CustomEmptyItem(R.dimen.dimen_24),
//        ActorPropertyItem(R.string.dialog_property_birthday, actorInfo.birthday),
//        CustomEmptyItem(R.dimen.dimen_12),
//        ActorPropertyItem(R.string.dialog_property_place_of_birth, actorInfo.placeOfBirth),
//        CustomEmptyItem(R.dimen.dimen_12),
//        ActorPropertyItem(R.string.dialog_property_popularity, actorInfo.popularity.toString()),
//        CustomEmptyItem(R.dimen.dimen_12),
//        DescriptionItem(actorInfo.biography),
//        CustomEmptyItem(R.dimen.dimen_32)
//    )

    private fun buildErrorItems(): List<Item> = listOf<Item>(ErrorItem())

    private fun handleTouchBtnInfo() {
//        val items = actorData?.let { buildItems(it.actorInfo) } ?: buildErrorItems()
//        setSingleEvent(ActorContract.SingleEvent.ShowDialogInfo(items))
    }
}