package com.evgenykuksov.moviebase.screens.overview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.evgenykuksov.domain.movies.model.MoviesCategory
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseFragment
import com.evgenykuksov.moviebase.screens.overview.items.RankDividerItem
import com.evgenykuksov.moviebase.screens.overview.items.RankItem
import com.google.android.material.tabs.TabLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class OverviewFragment : BaseFragment(R.layout.fragment_overview) {

    private val viewModel: OverviewViewModel by viewModel()
    private val adapterMovies: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var moviesSection = Section()
    private val adapterRank: GroupAdapter<GroupieViewHolder> = GroupAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeState()
        observeSingleEffect()
        viewModel.sendIntent(OverviewContract.Intent.Start)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
    }

    private fun initWidgets() {
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

        rvMovies.adapter = adapterMovies.apply { add(moviesSection) }
        rvRank.adapter = adapterRank.apply {
            for (i in 1..9) {
                if (i in 7..9) add(RankItem(R.color.overview_item_rank_default))
                else add(RankItem(R.color.overview_item_rank_active))

                if (i < 9) add(RankDividerItem())
            }
        }
    }

    private fun observeState() = lifecycleScope.launchWhenStarted {
        viewModel.state.collect {
            when (it.state) {
                is OverviewContract.OverviewState.Idle -> {
                }
                is OverviewContract.OverviewState.Loading -> {
                    moviesSection.update(it.state.listLoadingItems)
                }
                is OverviewContract.OverviewState.Success -> {
                    moviesSection.update(it.state.listItems)
                }
                is OverviewContract.OverviewState.Error -> {
                    moviesSection.update(it.state.listErrorItems)
                }
            }
        }
    }

    private fun observeSingleEffect() = lifecycleScope.launchWhenStarted {
        viewModel.singleEvent.collect {
            when (it) {
                is OverviewContract.SingleEvent.ToastError -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {

        fun newInstance() = OverviewFragment()
    }
}

internal const val TAB_NEW = 0
internal const val TAB_POPULAR = 1
internal const val TAB_TOP_RATED = 2