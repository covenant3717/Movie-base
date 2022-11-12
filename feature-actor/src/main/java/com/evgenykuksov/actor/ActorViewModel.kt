package com.evgenykuksov.actor

import android.view.Gravity
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.evgenykuksov.actor.navigation.ActorNavigation
import com.evgenykuksov.core.base.BaseViewModel
import com.evgenykuksov.core.items.CustomEmptyItem
import com.evgenykuksov.core.items.CustomTextItem
import com.evgenykuksov.domain.persons.PersonsUseCase
import com.evgenykuksov.domain.persons.model.ActorData
import com.evgenykuksov.domain.persons.model.ActorInfo
import com.xwray.groupie.kotlinandroidextensions.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ActorViewModel @Inject constructor(
//    private val actorId: Long,
    stateHandle: SavedStateHandle,
//    private val navigator: ActorNavigation,
    private val personsUseCase: PersonsUseCase
) : BaseViewModel<ActorContract.Intent, ActorContract.State, ActorContract.SingleEvent>() {

    private var actorData: ActorData? = null

    init {
        val actorId = stateHandle.get<Long>("arg_actor_id") ?: -1
        load(actorId)
    }

    override fun createInitialState() = ActorContract.State(null, R.dimen.dimen_40)

    override fun handleIntent(intent: ActorContract.Intent) {
        when (intent) {
            is ActorContract.Intent.InfoClicked -> handleInfoClicked()
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
                setState {
                    copy(
                        listPhotos = it.images,
                        pagerEndPaddingRes = if (it.images.size > 1) R.dimen.dimen_40 else R.dimen.dimen_16
                    )
                }
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

        // birthday
        CustomEmptyItem(R.dimen.dimen_24),
        CustomTextItem(
            textContentRes = R.string.dialog_property_birthday,
            styleRes = R.style.TextAppearance_MaterialComponents_Body2,
            colorRes = R.color.dialog_property_name,
            sideMarginsRes = R.dimen.dimen_20,
        ),
        CustomTextItem(
            textContent = actorInfo.birthday,
            styleRes = R.style.TextAppearance_MaterialComponents_Body2,
            colorRes = R.color.dialog_property_value,
            sideMarginsRes = R.dimen.dimen_20,
        ),

        // popularity
        CustomEmptyItem(R.dimen.dimen_10),
        CustomTextItem(
            textContentRes = R.string.dialog_property_popularity,
            styleRes = R.style.TextAppearance_MaterialComponents_Body2,
            colorRes = R.color.dialog_property_name,
            sideMarginsRes = R.dimen.dimen_20,
        ),
        CustomTextItem(
            textContent = actorInfo.popularity.toString(),
            styleRes = R.style.TextAppearance_MaterialComponents_Body2,
            colorRes = R.color.dialog_property_value,
            sideMarginsRes = R.dimen.dimen_20,
        ),

        // place of birth
        CustomEmptyItem(R.dimen.dimen_10),
        CustomTextItem(
            textContentRes = R.string.dialog_property_place_of_birth,
            styleRes = R.style.TextAppearance_MaterialComponents_Body2,
            colorRes = R.color.dialog_property_name,
            sideMarginsRes = R.dimen.dimen_20,
        ),
        CustomTextItem(
            textContent = actorInfo.placeOfBirth,
            styleRes = R.style.TextAppearance_MaterialComponents_Body2,
            colorRes = R.color.dialog_property_value,
            sideMarginsRes = R.dimen.dimen_20,
        ),

        // biography
        CustomEmptyItem(R.dimen.dimen_16),
        CustomTextItem(
            textContent = actorInfo.biography,
            colorRes = R.color.core_item_description_text,
            styleRes = R.style.TextAppearance_MaterialComponents_Subtitle2,
            sideMarginsRes = R.dimen.dimen_20
        ),
        CustomEmptyItem(R.dimen.dimen_40)
    )

    private fun handleInfoClicked() {
        val listItems = actorData?.let { buildInfoItems(it.actorInfo) } ?: buildErrorItems()
//        navigator.toBottomDialog(listItems)     // TODO: hilt migration
    }
}