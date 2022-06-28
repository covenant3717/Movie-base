package com.evgenykuksov.actor

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.ImageLoader
import com.evgenykuksov.actor.adapter.PhotoAdapter
import com.evgenykuksov.actor.adapter.PhotoPageTransformer
import com.evgenykuksov.core.base.BaseFragment
import com.evgenykuksov.core.di.COIL_EMPTY_LOADER
import com.evgenykuksov.core.extensions.collectLA
import com.evgenykuksov.core.extensions.setPaddings
import com.evgenykuksov.core.extensions.toast
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.android.synthetic.main.fragment_actor.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class ActorFragment : BaseFragment(R.layout.fragment_actor) {

    private val viewModel: ActorViewModel by viewModel { parametersOf(actorId) }
    private val emptyImageLoader: ImageLoader by inject(named(COIL_EMPTY_LOADER))
    private val adapterPhotos = PhotoAdapter(emptyImageLoader) { viewModel.sendIntent(ActorContract.Intent.InfoClicked) }
    private val actorId: Long by lazy { arguments?.getLong(ARG_ACTOR_ID) ?: throw IllegalStateException("No actorId") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply { scrimColor = Color.TRANSPARENT }
        enterTransition = MaterialElevationScale(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        getPersistentView(inflater, container)

    override fun initWidgets() {
        vpItems.apply {
            offscreenPageLimit = 5
            setPageTransformer(PhotoPageTransformer(5))
            adapter = adapterPhotos
        }
    }

    override fun observeState() {
        viewModel.state.collectLA(viewLifecycleOwner) {
            it.listPhotos?.let { adapterPhotos.setData(it) }
            it.pagerEndPaddingRes?.let { vpItems.setPaddings(endRes = it) }
        }
    }

    override fun observeSingleEffect() {
        viewModel.singleEvent.collectLA(viewLifecycleOwner) {
            when (it) {
                is ActorContract.SingleEvent.ToastError -> {
                    requireContext().toast(it.message, Toast.LENGTH_LONG)
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