package com.evgenykuksov.actor

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.evgenykuksov.actor.adapter.FullSizePhotoAdapter
import com.evgenykuksov.core.anim.ANIM_DURATION_250
import com.evgenykuksov.core.anim.ANIM_DURATION_350
import com.evgenykuksov.core.anim.animateAlpha
import com.evgenykuksov.core.base.BaseFragment
import com.evgenykuksov.core.di.COIL_EMPTY_LOADER
import com.evgenykuksov.core.extensions.collectLA
import com.evgenykuksov.core.extensions.gone
import com.evgenykuksov.core.extensions.toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.fragment_actor.*
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class ActorFragment : BaseFragment(R.layout.fragment_actor) {

    private val viewModel: ActorViewModel by viewModel { parametersOf(actorId) }
    private val emptyImageLoader: ImageLoader by inject(named(COIL_EMPTY_LOADER))
    private val adapterFullSizePhotos = FullSizePhotoAdapter(emptyImageLoader, emptyList())
    private val adapterSmallPhotos: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private val adapterInfo: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var photosSection = Section()
    private val actorId: Long by lazy { arguments?.getLong(ARG_ACTOR_ID) ?: throw IllegalStateException("No actorId") }
    private val actorPhoto: String by lazy { arguments?.getString(ARG_ACTOR_PHOTO).orEmpty() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply { scrimColor = Color.TRANSPARENT }
        enterTransition = MaterialElevationScale(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        getPersistentView(inflater, container) { }

    override fun initWidgets() {
        imgPhoto.apply {
            transitionName = actorPhoto
            load(actorPhoto, emptyImageLoader)
        }
        vpItems.apply {
            isUserInputEnabled = false
            offscreenPageLimit = 5
            adapter = adapterFullSizePhotos
        }
        rvItems.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, true)
            adapter = adapterSmallPhotos.apply { add(photosSection) }
        }
    }

    override fun observeState() {
        viewModel.state.collectLA(viewLifecycleOwner) {
            it.photo?.let {
                imgPhoto.animateAlpha(1f, 0f, ANIM_DURATION_250) {
                    imgPhoto.load(it, emptyImageLoader) {
                        listener(
                            onSuccess = { request, result ->
                                imgPhoto.animateAlpha(0f, 1f, ANIM_DURATION_350) {}
                            }
                        )
                    }
                }
            }
            it.delayUpdateItems?.let { delay(it) }
            it.listPhotos?.let {
                adapterFullSizePhotos.setData(it)
                imgPhoto.gone()
            }
            it.currentPhotoPosition?.let { vpItems.setCurrentItem(it, true) }
            it.listItems?.let { photosSection.update(it) }
        }
    }

    override fun observeSingleEffect() {
        viewModel.singleEvent.collectLA(viewLifecycleOwner) {
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

    companion object {

        private const val ARG_ACTOR_ID = "arg_actor_id"
        private const val ARG_ACTOR_PHOTO = "arg_actor_photo"

        fun createBundle(actorId: Long, actorPhoto: String) = Bundle().apply {
            putLong(ARG_ACTOR_ID, actorId)
            putString(ARG_ACTOR_PHOTO, actorPhoto)
        }
    }
}