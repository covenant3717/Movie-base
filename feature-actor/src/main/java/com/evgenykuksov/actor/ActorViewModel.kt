package com.evgenykuksov.actor

import androidx.lifecycle.viewModelScope
import com.evgenykuksov.domain.actors.ActorsUseCase
import com.evgenykuksov.domain.actors.model.ActorInfo
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.core.items.CustomEmptyItem
import com.evgenykuksov.core.items.DescriptionItem
import com.evgenykuksov.core.items.ErrorItem
import com.evgenykuksov.core.items.NameItem
import com.evgenykuksov.actor.items.ActorPropertyItem
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActorViewModel(
    private val actorsUseCase: ActorsUseCase,
) : BaseViewModel<ActorContract.Intent, ActorContract.State, ActorContract.SingleEvent>() {

    private var actorInfo: ActorInfo? = null

    override fun createInitialState() = ActorContract.State(null)

    override fun handleIntent(intent: ActorContract.Intent) {
        when (intent) {
            is ActorContract.Intent.LoadActorDetails -> load(intent.actorId)
            is ActorContract.Intent.TouchedBtnInfo -> handleTouchBtnInfo()
        }
    }

    private fun load(actorId: Long) = viewModelScope.launch {
        actorsUseCase.getActorInfo(actorId)
            .catch { exception ->
                setSingleEvent(ActorContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                actorInfo = it
                setState { copy(photo = it.profilePhoto) }
            }
    }

    private fun buildItems(actorInfo: ActorInfo): List<Item> = listOf(
        NameItem(actorInfo.name),
        CustomEmptyItem(R.dimen.dimen_24),
        ActorPropertyItem(R.string.dialog_property_birthday, actorInfo.birthday),
        CustomEmptyItem(R.dimen.dimen_12),
        ActorPropertyItem(R.string.dialog_property_place_of_birth, actorInfo.placeOfBirth),
        CustomEmptyItem(R.dimen.dimen_12),
        ActorPropertyItem(R.string.dialog_property_popularity, actorInfo.popularity.toString()),
        CustomEmptyItem(R.dimen.dimen_12),
        DescriptionItem(actorInfo.biography),
        CustomEmptyItem(R.dimen.dimen_32)
    )

    private fun buildErrorItems(): List<Item> = listOf<Item>(ErrorItem())

    private fun handleTouchBtnInfo() {
        val items = actorInfo?.let { buildItems(it) } ?: buildErrorItems()
        setSingleEvent(ActorContract.SingleEvent.ShowDialogInfo(items))
    }
}