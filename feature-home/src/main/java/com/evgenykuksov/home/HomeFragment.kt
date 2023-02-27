package com.evgenykuksov.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.evgenykuksov.core.ui.theme.ThemeColors
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.domain.movies.model.MoviesGrouping
import com.evgenykuksov.home.utils.MoviesCategory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private val tabHandler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(inflater.context).apply {
            setContent {
                val state by viewModel.state.collectAsState()
                HomeScreen(state)
            }
        }
    }

    @Composable
    private fun HomeScreen(state: HomeContract.State) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxHeight()
                .fillMaxWidth()
                .background(ThemeColors.core_background)
                .padding(top = 20.dp)
        ) {
            IconButton(modifier = Modifier, onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_reading_glass),
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
                    text = stringResource(R.string.title_categories),
                    color = colorResource(R.color.title_categories),
                    style = MaterialTheme.typography.h5
                )
                IconButton(modifier = Modifier.align(Alignment.Bottom), onClick = {}) {
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(R.drawable.ic_four_points),
                        tint = ThemeColors.core_icon,
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            ScrollableTabRow(state.category)
            Spacer(modifier = Modifier.height(24.dp))
            ListMovies(modifier = Modifier.weight(1f), state.movies)
            Spacer(modifier = Modifier.height(48.dp))
            Rating(state.rating)
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

    @Composable
    private fun ScrollableTabRow(selectedCategory: MoviesCategory) {
//        var state by remember { mutableStateOf(0) }
        Column {
            ScrollableTabRow(
                selectedTabIndex = selectedCategory.index,
                backgroundColor = ThemeColors.core_background,
                contentColor = colorResource(R.color.tab_underline),
                edgePadding = 0.dp,
                tabs = {
                    MoviesCategory.values().forEachIndexed { index, category ->
                        Tab(
                            titleRes = category.titleRes,
                            isSelected = selectedCategory.index == index,
                            onClick = {
                                viewModel.handleIntent(
                                    HomeContract.Intent.SelectCategory(MoviesCategory.getCategoryByIndex(index))
                                )
                            }
                        )
                    }
                }
            )
        }
    }

    @Composable
    private fun Tab(@StringRes titleRes: Int, isSelected: Boolean, onClick: () -> Unit) {
        Tab(
            text = {
                Text(
                    text = stringResource(titleRes),
                    style = MaterialTheme.typography.subtitle1
                )
            },
            selected = isSelected,
            onClick = { onClick() },
            selectedContentColor = colorResource(R.color.tab_selected),
            unselectedContentColor = colorResource(R.color.tab_default)
        )
    }

    @Composable
    private fun ListMovies(modifier: Modifier, movies: List<Movie>) {
        LazyHorizontalGrid(
            modifier = modifier,
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies.size) {
                MovieItem()
            }
        }
    }

    @Composable
    private fun MovieItem() {
        Card(
            modifier = Modifier
                .width(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    indication = rememberRipple(bounded = true, color = ThemeColors.core_white10),
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { }
                ),
            backgroundColor = Color.DarkGray,
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            content = { }
        )
    }

    @Composable
    private fun Rating(rating: Int?) {
        if (rating == null) return
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.title_your_rank),
                color = colorResource(id = R.color.core_white_80),
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = rating.toString(),
                color = colorResource(id = R.color.core_white_80),
                style = MaterialTheme.typography.subtitle1
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        DashedDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            thickness = 4.dp
        )
    }

    @Composable
    fun DashedDivider(
        thickness: Dp,
        color: Color = colorResource(R.color.item_rank_active),
        phase: Float = 0f,
        intervals: FloatArray = floatArrayOf(60f, 30f),
        modifier: Modifier
    ) {
        Canvas(
            modifier = modifier
        ) {
            val dividerHeight = thickness.toPx()
            drawRoundRect(
                color = color,
                style = Stroke(
                    width = dividerHeight,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = intervals,
                        phase = phase
                    )
                )
            )
        }
    }

    @Composable
    @Preview(showSystemUi = true)
    fun HomeScreenPreview() {
        HomeScreen(
            HomeContract.State(MoviesGrouping.Grid, MoviesCategory.NOW_PLAYING, emptyList(), 1354)
        )
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