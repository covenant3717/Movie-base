package com.evgenykuksov.moviebase.screens.actor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import coil.ImageLoader
import coil.load
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import com.evgenykuksov.moviebase.di.COIL_DEFAULT_LOADER
import com.evgenykuksov.moviebase.extansions.launchWhenStarted
import kotlinx.android.synthetic.main.activity_actor.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class ActorActivity : BaseActivity(R.layout.activity_actor) {

    private val viewModel: ActorViewModel by viewModel()
    private val defaultImageLoader: ImageLoader by inject(named(COIL_DEFAULT_LOADER))

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
        launchWhenStarted {
            viewModel.state.collect {
                imgPhoto.load(it.photo, defaultImageLoader)
            }
        }
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