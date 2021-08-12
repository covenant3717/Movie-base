package com.evgenykuksov.moviebase.screens.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MovieActivity : BaseActivity(R.layout.activity_movie) {

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidgets()
        intent.getLongExtra(ARG_MOVIE_ID, 0).let {
            viewModel.sendIntent(MovieContract.Intent.LoadMovieDetails(it))
        }
    }

    private fun initWidgets() {

    }

    companion object {

        fun newInstance(context: Context, movieId: Long) = Intent(context, MovieActivity::class.java)
            .apply { putExtra(ARG_MOVIE_ID, movieId) }
    }
}

private const val ARG_MOVIE_ID = "arg_movie_id"