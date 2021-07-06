package com.evgenykuksov.moviebase.screens.main

import com.evgenykuksov.moviebase.base.BaseViewModel

class MainViewModel : BaseViewModel<MainContract.Intent, MainContract.State, MainContract.SingleEvent>() {

    override fun createInitialState() = MainContract.State.Idle

    override fun handleIntent(intent: MainContract.Intent) {
        when (intent) {
            is MainContract.Intent.Start -> {}
        }
    }
}