package com.evgenykuksov.movie

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.core.anim.ANIM_DURATION_350
import com.evgenykuksov.core.anim.startAnimationAlpha
import com.evgenykuksov.core.base.BaseFragment
import com.evgenykuksov.core.di.CoilModule
import com.evgenykuksov.core.extensions.collectLA
import com.evgenykuksov.core.extensions.toHoursMinutes
import com.evgenykuksov.core.extensions.toast
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : BaseFragment(R.layout.fragment_movie) {

    private val viewModel: MovieViewModel by viewModels()
    @CoilModule.EmptyLoaderCoil
    @Inject
    lateinit var emptyImageLoader: ImageLoader
    private val adapterDetails: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private val adapterBackdrops: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var detailsSection = Section()
    private var backdropSection = Section()
    private val moviePoster: String by lazy { arguments?.getString(ARG_MOVIE_POSTER).orEmpty() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply { scrimColor = Color.TRANSPARENT }
        enterTransition = MaterialElevationScale(false)
        exitTransition = MaterialElevationScale(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        getPersistentView(inflater, container)

    override fun initWidgets() {
        imgBack.setOnClickListener { viewModel.sendIntent(MovieContract.Intent.Back) }
        imgPoster.apply {
            transitionName = moviePoster
            load(moviePoster, emptyImageLoader) {
                transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_16)))
            }
        }
        rvBackdrops.adapter = adapterBackdrops.apply { add(backdropSection) }
        rvDetails.adapter = adapterDetails.apply { add(detailsSection) }
    }

    override fun observeState() {
        viewModel.state.collectLA(viewLifecycleOwner) {
            it.backdrop?.let { backdrop ->
                imgBackdrop.apply {
                    load(backdrop, emptyImageLoader) {
                        listener(onSuccess = { request, metadata ->
                            startAnimationAlpha(0f, 1f, ANIM_DURATION_350)
                        })
                    }
                }
            }
            tvName.text = it.name
            tvDate.text = it.date
            tvDuration.text = it.duration?.toHoursMinutes()?.let {
                getString(R.string.movie_duration, it.first, it.second)
            }
            it.delayUpdateItems?.let { delay(it) }
            it.listBackdrops?.let { backdropSection.update(it) }
            it.listItems?.let { detailsSection.update(it) }
        }
    }

    override fun observeSingleEffect() {
        viewModel.singleEvent.collectLA(viewLifecycleOwner) {
            when (it) {
                is MovieContract.SingleEvent.ToastError -> {
                    requireContext().toast(it.message, Toast.LENGTH_LONG)
                }
            }
        }
    }

    companion object {

        private const val ARG_MOVIE_POSTER = "arg_movie_poster"
        private const val ARG_MOVIE_ID = "arg_movie_id"

        fun createBundle(movieId: Long, poster: String) = Bundle().apply {
            putString(ARG_MOVIE_POSTER, poster)
            putLong(ARG_MOVIE_ID, movieId)
        }
    }
}