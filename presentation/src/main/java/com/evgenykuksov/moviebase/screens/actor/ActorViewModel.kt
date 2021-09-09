package com.evgenykuksov.moviebase.screens.actor

import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.evgenykuksov.domain.actors.ActorsUseCase
import com.evgenykuksov.moviebase.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActorViewModel(
    private val actorsUseCase: ActorsUseCase,
    private val defaultImageLoader: ImageLoader
) : BaseViewModel<ActorContract.Intent, ActorContract.State, ActorContract.SingleEvent>() {

    override fun createInitialState() = ActorContract.State(null)

    override fun handleIntent(intent: ActorContract.Intent) {
        when (intent) {
            is ActorContract.Intent.LoadActorDetails -> load(intent.actorId)
        }
    }

    private fun load(actorId: Long) = viewModelScope.launch {
        actorsUseCase.getActorInfo(actorId)
            .catch { exception ->
                setSingleEvent(ActorContract.SingleEvent.ToastError(exception.localizedMessage.orEmpty()))
            }
            .collect {
                setState { copy(photo = it.profilePhoto) }
            }
    }
}