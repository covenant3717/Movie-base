package com.evgenykuksov.moviebase.screens.actor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import com.evgenykuksov.moviebase.di.COIL_DEFAULT_LOADER
import com.evgenykuksov.moviebase.extansions.launchWhenStarted
import com.evgenykuksov.moviebase.extansions.toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.activity_actor.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class ActorActivity : BaseActivity(R.layout.activity_actor) {

    private val viewModel: ActorViewModel by viewModel()
    private val defaultImageLoader: ImageLoader by inject(named(COIL_DEFAULT_LOADER))
    private val adapterInfo: GroupAdapter<GroupieViewHolder> = GroupAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getLongExtra(ARG_ACTOR_ID, 0).let {
            viewModel.sendIntent(ActorContract.Intent.LoadActorDetails(it))
        }
    }

    override fun initWidgets() {
        btnInfo.setOnClickListener { viewModel.sendIntent(ActorContract.Intent.TouchedBtnInfo) }
    }

    override fun observeState() {
        launchWhenStarted {
            viewModel.state.collect {
                imgPhoto.load(it.photo, defaultImageLoader)
            }
        }
    }

    private fun showInfoDialog(listItems: List<Item>) {
        val view = layoutInflater.inflate(R.layout.actor_dialog_person_info, null).apply {
            findViewById<RecyclerView>(R.id.rvInfo).adapter = adapterInfo.apply {
                clear()
                addAll(listItems)
            }
        }

        BottomSheetDialog(this, R.style.ActorDialogInfo)
            .apply { setContentView(view) }
            .show()
    }

    override fun observeSingleEffect() {
        launchWhenStarted {
            viewModel.singleEvent.collect {
                when (it) {
                    is ActorContract.SingleEvent.ToastError -> {
                        toast(it.message, Toast.LENGTH_LONG)
                    }
                    is ActorContract.SingleEvent.ShowDialogInfo -> {
                        showInfoDialog(it.listItems)
                    }
                }
            }
        }
    }

    companion object {

        fun newInstance(context: Context, actorId: Long) =
            Intent(context, ActorActivity::class.java)
                .apply { putExtra(ARG_ACTOR_ID, actorId) }
    }
}

private const val ARG_ACTOR_ID = "arg_actor_id"