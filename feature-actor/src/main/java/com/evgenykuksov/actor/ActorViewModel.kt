package com.evgenykuksov.actor

import android.view.Gravity
import androidx.lifecycle.viewModelScope
import com.evgenykuksov.domain.persons.PersonsUseCase
import com.evgenykuksov.domain.persons.model.Actor
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.actor.items.ActorPropertyItem
import com.evgenykuksov.actor.items.DescriptionItem
import com.evgenykuksov.core.items.*
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActorViewModel(
    private val actorId: Long,
    private val personsUseCase: PersonsUseCase,
) : BaseViewModel<ActorContract.Intent, ActorContract.State, ActorContract.SingleEvent>() {

    private var actorInfo: Actor? = null

    init {
        load(actorId)
    }

    override fun createInitialState() = ActorContract.State(null)

    override fun handleIntent(intent: ActorContract.Intent) {
        when (intent) {
            is ActorContract.Intent.TouchedBtnInfo -> handleTouchBtnInfo()
        }
    }

    private fun load(actorId: Long) = viewModelScope.launch {
        personsUseCase.getActor(actorId)
            .catch { exception ->
                setSingleEvent(ActorContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                actorInfo = it
                setState { copy(photo = it.profilePhoto) }
            }
    }

    private fun buildItems(actor: Actor): List<Item> = listOf(
        CustomTextItem(
            textContent = actor.name,
            styleRes = R.style.TextAppearance_MaterialComponents_Headline6,
            colorRes = R.color.dialog_actor_name,
            startPaddingRes = R.dimen.dimen_20,
            endPaddingRes = R.dimen.dimen_20,
            gravityState = Gravity.CENTER_HORIZONTAL
        ),
        CustomEmptyItem(R.dimen.dimen_24),
        ActorPropertyItem(R.string.dialog_property_birthday, actor.birthday),
        CustomEmptyItem(R.dimen.dimen_12),
        ActorPropertyItem(R.string.dialog_property_place_of_birth, actor.placeOfBirth),
        CustomEmptyItem(R.dimen.dimen_12),
        ActorPropertyItem(R.string.dialog_property_popularity, actor.popularity.toString()),
        CustomEmptyItem(R.dimen.dimen_12),
        DescriptionItem(actor.biography),
        CustomEmptyItem(R.dimen.dimen_32)
    )

    private fun buildErrorItems(): List<Item> = listOf<Item>(ErrorItem())

    private fun handleTouchBtnInfo() {
        val items = actorInfo?.let { buildItems(it) } ?: buildErrorItems()
        setSingleEvent(ActorContract.SingleEvent.ShowDialogInfo(items))
    }
}