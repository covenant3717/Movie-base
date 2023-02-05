package com.evgenykuksov.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.evgenykuksov.core.anim.ANIM_DURATION_250
import com.evgenykuksov.core.anim.startAnimationAlpha
import com.evgenykuksov.core.extensions.*
import com.evgenykuksov.domain.movies.model.MoviesCategory
import com.evgenykuksov.core.base.BaseFragment
import com.evgenykuksov.core.ui.theme.ThemeColors
import com.evgenykuksov.domain.movies.model.MoviesGrouping
import com.google.android.material.tabs.TabLayout
import com.google.android.material.transition.MaterialElevationScale
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private val titles = listOf(R.string.tab_upcoming, R.string.tab_new, R.string.tab_popular, R.string.tab_top_rated)
    private val tabHandler by lazy { Handler(Looper.getMainLooper()) }
    private var moviesSection = Section()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(inflater.context).apply {
            setContent {
                HomeScreen()
            }
        }
    }

    @Composable
    private fun HomeScreen() {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxHeight()
                .fillMaxWidth()
                .background(ThemeColors.core_background)
                .padding(top = 20.dp)
                .clickable(onClick = { }, indication = rememberRipple(
                    bounded = true,
                    color = ThemeColors.core_white10,
                ), interactionSource = remember { MutableInteractionSource() })
        ) {
            IconButton(modifier = Modifier, onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_reading_glass),
                    tint = ThemeColors.core_icon,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = stringResource(id = R.string.title_categories),
                    color = colorResource(id = R.color.title_categories),
                    style = MaterialTheme.typography.h5
                )
                IconButton(modifier = Modifier.align(Alignment.Bottom), onClick = {}) {
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = R.drawable.ic_four_points),
                        tint = ThemeColors.core_icon,
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            ScrollableTabRow(titles)
        }
    }

    @Composable
    private fun ScrollableTabRow(titles: List<Int>) {
        var state by remember { mutableStateOf(0) }
        Column {
            ScrollableTabRow(
                selectedTabIndex = state,
                backgroundColor = ThemeColors.core_background,
                contentColor = colorResource(id = R.color.tab_underline),
                edgePadding = 0.dp,
                tabs = {
                    titles.forEachIndexed { index, titleId ->
                        Tab(
                            text = {
                                Text(
                                    text = stringResource(titleId),
                                    style = MaterialTheme.typography.subtitle1
                                )
                            },
                            selected = state == index,
                            onClick = { state = index },
                            selectedContentColor = colorResource(id = R.color.tab_selected),
                            unselectedContentColor = colorResource(id = R.color.tab_default),
                        )
                    }
                }
            )
        }
    }

    @Composable
    @Preview(showSystemUi = true)
    fun HomeScreenPreview() {
        HomeScreen()
    }

/*
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
*/
}