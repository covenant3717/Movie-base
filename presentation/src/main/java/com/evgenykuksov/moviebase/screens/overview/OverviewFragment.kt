package com.evgenykuksov.moviebase.screens.overview

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseFragment
import com.evgenykuksov.moviebase.screens.overview.items.RankItem
import com.evgenykuksov.moviebase.screens.overview.items.SpaceDividerItem
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class OverviewFragment : BaseFragment(R.layout.fragment_overview) {

    private val viewModel: OverviewViewModel by viewModel()
    private val adapterMovies: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var updatingGroupMovies = Section()
    private val adapterRank: GroupAdapter<GroupieViewHolder> = GroupAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeState()
        observeSingleEffect()
        viewModel.setIntent(OverviewContract.Intent.Start)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
    }

    private fun initWidgets() {
        //        rvItems.adapter = adapter.apply { add(updatingGroup) }
        rvRank.adapter = adapterRank.apply {
            for (i in 1..9) {
                if (i in 7..9) add(RankItem(R.color.overview_item_rank_default))
                else add(RankItem(R.color.overview_item_rank_active))

                if (i < 9) add(SpaceDividerItem)
            }
        }
    }

    private fun observeState() = lifecycleScope.launchWhenStarted {
        viewModel.state.collect {
            when (it) {
                is OverviewContract.State.Idle -> {
//                    pb.isVisible = false
                }
                is OverviewContract.State.Loading -> {
//                    pb.isVisible = true
                }
                is OverviewContract.State.Success -> {
//                    pb.isVisible = false
//                    updatingGroup.update(it.list)
                }
            }
        }
    }

    private fun observeSingleEffect() = lifecycleScope.launchWhenStarted {
        viewModel.singleEvent.collect {
            when (it) {
                is OverviewContract.SingleEvent.ToastError -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {

        fun newInstance() = OverviewFragment()
    }
}