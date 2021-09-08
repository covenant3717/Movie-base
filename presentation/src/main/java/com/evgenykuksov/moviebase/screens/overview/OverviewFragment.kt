package com.evgenykuksov.moviebase.screens.overview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.evgenykuksov.domain.movies.model.MoviesCategory
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseFragment
import com.evgenykuksov.moviebase.extansions.*
import com.evgenykuksov.moviebase.screens.movie.MovieActivity
import com.google.android.material.tabs.TabLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class OverviewFragment : BaseFragment(R.layout.fragment_overview) {

    private val viewModel: OverviewViewModel by viewModel()
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var moviesSection = Section()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.sendIntent(OverviewContract.Intent.Start)
    }

    override fun initWidgets() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    TAB_NEW -> viewModel.sendIntent(OverviewContract.Intent.SelectCategory(MoviesCategory.New))
                    TAB_POPULAR -> viewModel.sendIntent(OverviewContract.Intent.SelectCategory(MoviesCategory.Popular))
                    TAB_TOP_RATED -> viewModel.sendIntent(OverviewContract.Intent.SelectCategory(MoviesCategory.TopRated))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        rvMovies.adapter = adapter.apply { add(moviesSection) }
    }

    override fun observeState() {
        launchWhenStarted {
            viewModel.state.collect {
                it.listItems?.let { list -> moviesSection.update(list) }
                if (it.rating.isNotNull()) {
                    tvRating.apply {
                        text = it.rating?.insertSpaces(3)
                        fadeTo(true)
                    }
                }
            }
        }
    }

    override fun observeSingleEffect() {
        launchWhenStarted {
            viewModel.singleEvent.collect {
                when (it) {
                    is OverviewContract.SingleEvent.ToastError -> {
                        requireContext().toast(it.message, Toast.LENGTH_LONG)
                    }
                    is OverviewContract.SingleEvent.StartMovieActivity -> {
                        startActivity(MovieActivity.newInstance(requireContext(), it.movieId))
                    }
                }
            }
        }
    }

    companion object {

        fun newInstance() = OverviewFragment()
    }
}

private const val TAB_NEW = 0
private const val TAB_POPULAR = 1
private const val TAB_TOP_RATED = 2