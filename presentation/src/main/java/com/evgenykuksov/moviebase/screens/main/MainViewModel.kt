package com.evgenykuksov.moviebase.screens.main

import com.evgenykuksov.moviebase.base.BaseViewModel
import com.evgenykuksov.moviebase.screens.bookmarks.BookmarksFragment
import com.evgenykuksov.moviebase.screens.overview.OverviewFragment

class MainViewModel : BaseViewModel<MainContract.Intent, MainContract.State, MainContract.SingleEvent>() {

    override fun createInitialState() = MainContract.State(OverviewFragment::class.java.simpleName)

    override fun handleIntent(intent: MainContract.Intent) {
        when (intent) {
            is MainContract.Intent.OverviewTouch -> setState { copy(fragmentName = OverviewFragment::class.java.simpleName) }
            is MainContract.Intent.BookmarksTouch -> setState { copy(fragmentName = BookmarksFragment::class.java.simpleName) }
        }
    }
}