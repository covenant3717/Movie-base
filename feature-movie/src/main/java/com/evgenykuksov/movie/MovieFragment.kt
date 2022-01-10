package com.evgenykuksov.movie

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.core.anim.animateAlpha
import com.evgenykuksov.core.extensions.launchWhenStarted
import com.evgenykuksov.core.base.BaseFragment
import com.evgenykuksov.core.di.COIL_DEFAULT_LOADER
import com.evgenykuksov.core.extensions.toast
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class MovieFragment : BaseFragment(R.layout.fragment_movie) {

    private val viewModel: MovieViewModel by viewModel { parametersOf(movieId) }
    private val defaultImageLoader: ImageLoader by inject(named(COIL_DEFAULT_LOADER))
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var detailsSection = Section()
    private val movieId: Long by lazy { arguments?.getLong(ARG_MOVIE_ID) ?: throw IllegalStateException("No movieId") }
    private val moviePoster: String by lazy {
        arguments?.getString(ARG_MOVIE_POSTER) ?: throw IllegalStateException("No poster")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply { scrimColor = Color.TRANSPARENT }
        enterTransition = MaterialElevationScale(false)
        exitTransition = MaterialElevationScale(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        getPersistentView(inflater, container) { }

    override fun initWidgets() {
        getToolbar()?.setNavigationOnClickListener { viewModel.sendIntent(MovieContract.Intent.Back) }
        imgPoster.apply {
            transitionName = moviePoster
            load(moviePoster, defaultImageLoader) {
                transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_16)))
            }
        }
        rvDetails.adapter = adapter.apply { add(detailsSection) }
    }

    override fun observeState() {
        launchWhenStarted {
            viewModel.state.collect {
                imgBackdrop.apply {
                    load(it.backdrop, defaultImageLoader)
                    animateAlpha(0f, 1f, 1000) {}
                }
                tvName.text = it.name
                tvDate.text = it.date
                it.listItems?.let { list -> detailsSection.update(list) }
            }
        }
    }

    override fun observeSingleEffect() {
        launchWhenStarted {
            viewModel.singleEvent.collect {
                when (it) {
                    is MovieContract.SingleEvent.ToastError -> {
                        requireContext().toast(it.message, Toast.LENGTH_LONG)
                    }
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