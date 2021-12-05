package com.evgenykuksov.actor

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.evgenykuksov.core.anim.startAnimationScaleWithBackward
import com.evgenykuksov.core.extensions.launchWhenStarted
import com.evgenykuksov.core.extensions.toast
import com.evgenykuksov.core.base.BaseFragment
import com.evgenykuksov.core.di.COIL_DEFAULT_LOADER
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.fragment_actor.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class ActorFragment : BaseFragment(R.layout.fragment_actor) {

    private val viewModel: ActorViewModel by inject()
    private val defaultImageLoader: ImageLoader by inject(named(COIL_DEFAULT_LOADER))
    private val adapterInfo: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private val actorId: Long by lazy { arguments?.getLong(ARG_ACTOR_ID) ?: throw IllegalStateException("No actorId") }
    private val actorPhoto: String by lazy { arguments?.getString(ARG_ACTOR_PHOTO, "") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply { scrimColor = Color.TRANSPARENT }
        enterTransition = MaterialElevationScale(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        getPersistentView(inflater, container)

    override fun initWidgets() {
        viewModel.sendIntent(ActorContract.Intent.LoadActorDetails(actorId))

        btnInfo.setOnClickListener {
            it.startAnimationScaleWithBackward(0.9f) {
                viewModel.sendIntent(ActorContract.Intent.TouchedBtnInfo)
            }
        }
    }

    override fun observeState() {
        launchWhenStarted {
            viewModel.state.collect {
                imgPhoto.apply {
                    transitionName = actorPhoto
                    load(actorPhoto, defaultImageLoader)
                }
            }
        }
    }

    private fun showInfoDialog(listItems: List<Item>) {
        val view = layoutInflater.inflate(R.layout.dialog_person_info, null).apply {
            findViewById<RecyclerView>(R.id.rvInfo).adapter = adapterInfo.apply {
                clear()
                addAll(listItems)
            }
        }

        BottomSheetDialog(requireContext(), R.style.DialogInfo)
            .apply { setContentView(view) }
            .show()
    }

    override fun observeSingleEffect() {
        launchWhenStarted {
            viewModel.singleEvent.collect {
                when (it) {
                    is ActorContract.SingleEvent.ToastError -> {
                        requireContext().toast(it.message, Toast.LENGTH_LONG)
                    }
                    is ActorContract.SingleEvent.ShowDialogInfo -> {
                        showInfoDialog(it.listItems)
                    }
                }
            }
        }
    }

    companion object {

        private const val ARG_ACTOR_ID = "arg_actor_id"
        private const val ARG_ACTOR_PHOTO = "arg_actor_photo"

        fun createBundle(actorId: Long, actorPhoto: String) = Bundle().apply {
            putLong(ARG_ACTOR_ID, actorId)
            putString(ARG_ACTOR_PHOTO, actorPhoto)
        }
    }
}