package com.evgenykuksov.moviebase.screens.actor

import com.evgenykuksov.moviebase.base.BaseViewModel

class ActorViewModel : BaseViewModel<ActorContract.Intent, ActorContract.State, ActorContract.SingleEvent>() {

    override fun createInitialState() = ActorContract.State(null)

    override fun handleIntent(intent: ActorContract.Intent) {
        when (intent) {
            is ActorContract.Intent.LoadActorDetails -> setState { copy(photo = "") }
        }
    }
}