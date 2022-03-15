package com.evgenykuksov.actor

import android.view.Gravity
import androidx.lifecycle.viewModelScope
import com.evgenykuksov.actor.items.ActorPropertyItem
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.core.items.CustomEmptyItem
import com.evgenykuksov.core.items.CustomTextItem
import com.evgenykuksov.domain.persons.PersonsUseCase
import com.evgenykuksov.domain.persons.model.ActorData
import com.evgenykuksov.domain.persons.model.ActorInfo
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ActorViewModel(
    private val actorId: Long,
    private val personsUseCase: PersonsUseCase
) : BaseViewModel<ActorContract.Intent, ActorContract.State, ActorContract.SingleEvent>() {

    private var actorData: ActorData? = null

    init {
        load(actorId)
    }

    override fun createInitialState() = ActorContract.State(null)

    override fun handleIntent(intent: ActorContract.Intent) {
        when (intent) {
            // todo: handle intents
        }
    }

    private fun load(actorId: Long) = viewModelScope.launch {
        personsUseCase.getActorData(actorId)
            .onStart { setState { copy(listPhotos = listOf(null, null, null)) } }
            .catch { exception ->
                setSingleEvent(ActorContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                actorData = it
                delay(1000)
                setState { copy(listPhotos = it.images,) }
            }
    }

    private fun buildInfoItems(actorInfo: ActorInfo): List<Item> = listOf(
        CustomTextItem(
            textContent = actorInfo.name,
            styleRes = R.style.TextAppearance_MaterialComponents_Headline6,
            colorRes = R.color.dialog_actor_name,
            sideMarginsRes = R.dimen.dimen_20,
            gravityState = Gravity.CENTER_HORIZONTAL
        ),
        CustomEmptyItem(R.dimen.dimen_24),
        ActorPropertyItem(R.string.dialog_property_birthday, actorInfo.birthday),
        CustomEmptyItem(R.dimen.dimen_8),
        ActorPropertyItem(R.string.dialog_property_place_of_birth, actorInfo.placeOfBirth),
        CustomEmptyItem(R.dimen.dimen_8),
        ActorPropertyItem(R.string.dialog_property_popularity, actorInfo.popularity.toString()),
        CustomEmptyItem(R.dimen.dimen_16),
        CustomTextItem(
            textContent = actorInfo.biography,
            colorRes = R.color.core_item_description_text,
            styleRes = R.style.TextAppearance_MaterialComponents_Subtitle2,
            sideMarginsRes = R.dimen.dimen_20
        ),
        CustomEmptyItem(R.dimen.dimen_32)
    )

    private fun handleTouchBtnInfo() {
        val items = actorData?.let { buildInfoItems(it.actorInfo) } ?: buildErrorItems()
        setSingleEvent(ActorContract.SingleEvent.ShowDialogInfo(items))
    }
}