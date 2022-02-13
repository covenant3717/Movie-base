package com.evgenykuksov.actor

import android.view.Gravity
import androidx.lifecycle.viewModelScope
import com.evgenykuksov.domain.persons.PersonsUseCase
import com.evgenykuksov.domain.persons.model.ActorInfo
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.actor.items.ActorPropertyItem
import com.evgenykuksov.actor.items.DescriptionItem
import com.evgenykuksov.core.items.*
import com.evgenykuksov.domain.persons.model.ActorData
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActorViewModel(
    private val actorId: Long,
    private val personsUseCase: PersonsUseCase,
) : BaseViewModel<ActorContract.Intent, ActorContract.State, ActorContract.SingleEvent>() {

    private var actorData: ActorData? = null

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
        personsUseCase.getActorData(actorId)
            .catch { exception ->
                setSingleEvent(ActorContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                actorData = it
                setState { copy(photo = it.actorInfo.profilePhoto) }
            }
    }

    private fun buildItems(actorInfo: ActorInfo): List<Item> = listOf(
        CustomTextItem(
            textContent = actorInfo.name,
            styleRes = R.style.TextAppearance_MaterialComponents_Headline6,
            colorRes = R.color.dialog_actor_name,
            startPaddingRes = R.dimen.dimen_20,
            endPaddingRes = R.dimen.dimen_20,
            gravityState = Gravity.CENTER_HORIZONTAL
        ),
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
        val items = actorData?.let { buildItems(it.actorInfo) } ?: buildErrorItems()
        setSingleEvent(ActorContract.SingleEvent.ShowDialogInfo(items))
    }
}