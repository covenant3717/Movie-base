package com.evgenykuksov.moviebase.screens.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import coil.ImageLoader
import coil.load
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import com.evgenykuksov.moviebase.di.COIL_DEFAULT_LOADER
import com.evgenykuksov.moviebase.extansions.launchWhenStarted
import com.evgenykuksov.moviebase.extansions.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MovieActivity : BaseActivity(R.layout.activity_movie) {

    private val viewModel: MovieViewModel by viewModel()
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
                        toast(it.message, Toast.LENGTH_LONG)
                    }
                }
            }
        }
    }

    companion object {

        fun newInstance(context: Context, movieId: Long) = Intent(context, MovieActivity::class.java)
            .apply { putExtra(ARG_MOVIE_ID, movieId) }
    }
}

private const val ARG_MOVIE_ID = "arg_movie_id"