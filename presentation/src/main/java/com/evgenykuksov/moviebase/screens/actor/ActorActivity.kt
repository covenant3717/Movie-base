package com.evgenykuksov.moviebase.screens.actor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ActorActivity : BaseActivity(R.layout.activity_actor) {

    private val viewModel: ActorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getLongExtra(ARG_ACTOR_ID, 0).let {
            viewModel.sendIntent(ActorContract.Intent.LoadActorDetails(it))
        }
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

    companion object {

        fun newInstance(context: Context, actorId: Long) = Intent(context, ActorActivity::class.java)
            .apply { putExtra(ARG_ACTOR_ID, actorId) }
    }
}

private const val ARG_ACTOR_ID = "arg_actor_id"