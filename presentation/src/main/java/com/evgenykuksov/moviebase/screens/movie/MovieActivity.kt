package com.evgenykuksov.moviebase.screens.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class MovieActivity : BaseActivity(R.layout.activity_movie) {

    private val viewModel: MovieViewModel by viewModel()
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var detailsSection = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidgets()
        observeState()
        observeSingleEffect()
        intent.getLongExtra(ARG_MOVIE_ID, 0).let {
            viewModel.sendIntent(MovieContract.Intent.LoadMovieDetails(it))
        }
    }

    private fun initWidgets() {
        rvDetails.adapter = adapter.apply { add(detailsSection) }
    }

    private fun observeState() = lifecycleScope.launchWhenStarted {
        viewModel.state.collect {
            it.listItems?.let { list -> detailsSection.update(list) }
        }
    }

    private fun observeSingleEffect() = lifecycleScope.launchWhenStarted {
        viewModel.singleEvent.collect {
            when (it) {
                is MovieContract.SingleEvent.ToastError -> {
                    Toast.makeText(this@MovieActivity, it.message, Toast.LENGTH_LONG).show()
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