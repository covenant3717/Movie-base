package com.evgenykuksov.movie

import android.content.Context
import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getLongExtra(ARG_MOVIE_ID, 0).let {
            viewModel.sendIntent(MovieContract.Intent.LoadMovieDetails(it))
        }
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
                    is MovieContract.SingleEvent.StartActorActivity -> {
//                        startActivity(ActorActivity.newInstance(this, it.actorId))
                    }
                }
            }
        }
    }

    companion object {

        private const val ARG_MOVIE_ID = "arg_movie_id"

        fun newInstance(context: Context, movieId: Long) = Intent(context, MovieFragment::class.java)
            .apply { putExtra(ARG_MOVIE_ID, movieId) }
    }
}