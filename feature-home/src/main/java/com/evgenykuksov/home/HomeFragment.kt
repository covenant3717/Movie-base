package com.evgenykuksov.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.evgenykuksov.core.anim.animateAlpha
import com.evgenykuksov.core.extensions.*
import com.evgenykuksov.domain.movies.model.MoviesCategory
import com.evgenykuksov.core.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.transition.MaterialElevationScale
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by inject()
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var moviesSection = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        getPersistentView(inflater, container) {
            viewModel.sendIntent(HomeContract.Intent.Start)
        }

    override fun initWidgets() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    TAB_NEW -> viewModel.sendIntent(HomeContract.Intent.SelectCategory(MoviesCategory.New))
                    TAB_POPULAR -> viewModel.sendIntent(HomeContract.Intent.SelectCategory(MoviesCategory.Popular))
                    TAB_TOP_RATED -> viewModel.sendIntent(HomeContract.Intent.SelectCategory(MoviesCategory.TopRated))
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
                it.rating?.insertSpaces(3)?.let { rating ->
                    if (rating != tvRating.text) {
                        tvRating.text = rating
                        tvRating.animateAlpha(0f, 1f) {}
                    }
                }
            }
        }
    }

    override fun observeSingleEffect() {
        launchWhenStarted {
            viewModel.singleEvent.collect {
                when (it) {
                    is HomeContract.SingleEvent.ToastError -> {
                        requireContext().toast(it.message, Toast.LENGTH_LONG)
                    }
                }
            }
        }
    }

    companion object {

        private const val TAB_NEW = 0
        private const val TAB_POPULAR = 1
        private const val TAB_TOP_RATED = 2
    }
}