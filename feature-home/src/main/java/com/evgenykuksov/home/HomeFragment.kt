package com.evgenykuksov.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.evgenykuksov.core.anim.ANIM_DURATION_250
import com.evgenykuksov.core.anim.startAnimationAlpha
import com.evgenykuksov.core.extensions.*
import com.evgenykuksov.domain.movies.model.MoviesCategory
import com.evgenykuksov.core.base.BaseFragment
import com.evgenykuksov.domain.movies.model.MoviesGrouping
import com.google.android.material.tabs.TabLayout
import com.google.android.material.transition.MaterialElevationScale
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private val tabHandler by lazy { Handler(Looper.getMainLooper()) }
    private var moviesSection = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        getPersistentView(inflater, container)

    override fun initWidgets() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // delay fixes the interruption of the indicator animation
                tabHandler.removeCallbacksAndMessages(null)
                tabHandler.postDelayed({
                    when (tab?.position) {
                        MoviesCategory.UPCOMING.position ->
                            viewModel.sendIntent(HomeContract.Intent.SelectCategory(MoviesCategory.UPCOMING))
                        MoviesCategory.NOW_PLAYING.position ->
                            viewModel.sendIntent(HomeContract.Intent.SelectCategory(MoviesCategory.NOW_PLAYING))
                        MoviesCategory.POPULAR.position ->
                            viewModel.sendIntent(HomeContract.Intent.SelectCategory(MoviesCategory.POPULAR))
                        MoviesCategory.TOP_RATED.position ->
                            viewModel.sendIntent(HomeContract.Intent.SelectCategory(MoviesCategory.TOP_RATED))
                    }
                }, ANIM_DURATION_250)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        btnToggleGrouping.setOnCheckedChangeListener { v, isChecked ->
            viewModel.sendIntent(
                if (isChecked) HomeContract.Intent.ChangeGrouping(MoviesGrouping.Grid)
                else HomeContract.Intent.ChangeGrouping(MoviesGrouping.Linear)
            )
        }
        rvMovies.adapter = adapter.apply { add(moviesSection) }
    }

    override fun observeState() {
        viewModel.state.collectLA(viewLifecycleOwner) {
            when (it.grouping) {
                MoviesGrouping.Linear -> {
                    btnToggleGrouping.isChecked = false
                    (rvMovies.layoutManager as GridLayoutManager).spanCount = 1
                }
                MoviesGrouping.Grid -> {
                    btnToggleGrouping.isChecked = true
                    (rvMovies.layoutManager as GridLayoutManager).spanCount = 2
                }
            }
            tabLayout.getTabAt(it.category.position)?.select()
            it.listItems?.let { list -> moviesSection.update(list) }
            it.rating?.insertSpaces(3)?.let { rating ->
                if (rating != tvRating.text) {
                    tvRating.text = rating
                    tvRating.startAnimationAlpha(0f, 1f)
                }
            }
        }
    }

    override fun observeSingleEffect() {
        viewModel.singleEvent.collectLA(viewLifecycleOwner) {
            when (it) {
                is HomeContract.SingleEvent.ToastError -> {
                    requireContext().toast(it.message, Toast.LENGTH_LONG)
                }
            }
        }
    }
}