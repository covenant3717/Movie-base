package com.evgenykuksov.moviebase.screens.actor

import android.os.Bundle
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ActorActivity : BaseActivity(R.layout.activity_actor) {

    private val viewModel: ActorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initWidgets() {
        // todo
    }

    override fun observeState() {
        // todo
    }

    override fun observeSingleEffect() {
        // todo
    }
}