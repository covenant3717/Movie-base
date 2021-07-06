package com.evgenykuksov.moviebase.screens.overview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseFragment
import com.evgenykuksov.moviebase.screens.main.MainContract
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class OverviewFragment : BaseFragment(R.layout.fragment_overview) {

    private val viewModel: OverviewViewModel by viewModel()
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var updatingGroup = Section()

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