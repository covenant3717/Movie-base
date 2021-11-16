package com.evgenykuksov.movie

import android.os.Bundle
import android.widget.Toast
import coil.ImageLoader
import coil.load
import com.evgenykuksov.core.extensions.launchWhenStarted
import com.evgenykuksov.core.base.BaseFragment
import com.evgenykuksov.core.di.COIL_DEFAULT_LOADER
import com.evgenykuksov.core.extensions.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MovieFragment : BaseFragment(R.layout.fragment_movie) {

    private val viewModel: MovieViewModel by inject()
    private val defaultImageLoader: ImageLoader by inject(named(COIL_DEFAULT_LOADER))
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var detailsSection = Section()
    private val movieId: Long by lazy { arguments?.getLong(ARG_MOVIE_ID) ?: throw IllegalStateException("No movieId") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.sendIntent(MovieContract.Intent.LoadMovieDetails(movieId))
    }

    override fun initWidgets() {
        rvDetails.adapter = adapter.apply { add(detailsSection) }
    }

    override fun observeState() {
        launchWhenStarted {
            viewModel.state.collect {
                imgPoster.load(it.poster, defaultImageLoader)
                delay(it.delayUpdateItems)
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

        private const val ARG_MOVIE_ID = "arg_movie_id"

        fun createBundle(movieId: Long) = Bundle().apply {
            putLong(ARG_MOVIE_ID, movieId)
        }
    }
}