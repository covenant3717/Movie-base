package com.evgenykuksov.moviebase.screens.main

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import com.evgenykuksov.moviebase.screens.bookmarks.BookmarksFragment
import com.evgenykuksov.moviebase.screens.overview.OverviewFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()
    private val overviewFragment by lazy { OverviewFragment.newInstance() }
    private val bookmarksFragment by lazy { BookmarksFragment.newInstance() }
    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private var updatingGroup = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidgets()
        observeState()
        observeSingleEffect()
        viewModel.setIntent(MainContract.Intent.Start)
    }

    private fun initWidgets() {
        setFragment(overviewFragment)
//        rvItems.adapter = adapter.apply { add(updatingGroup) }
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_overview -> true.also { setFragment(overviewFragment) }
                R.id.page_list -> true
                R.id.page_bookmark -> true.also { setFragment(bookmarksFragment) }
                R.id.page_profile -> true
                else -> false
            }
        }
    }

    private fun observeState() = lifecycleScope.launchWhenStarted {
        viewModel.state.collect {
            when (it) {
                is MainContract.State.Idle -> {
//                    pb.isVisible = false
                }
                is MainContract.State.Loading -> {
//                    pb.isVisible = true
                }
                is MainContract.State.Success -> {
//                    pb.isVisible = false
//                    updatingGroup.update(it.list)
                }
            }
        }
    }

    private fun observeSingleEffect() = lifecycleScope.launchWhenStarted {
        viewModel.singleEvent.collect {
            when (it) {
                is MainContract.SingleEvent.ToastError -> {
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setFragment(fragment: Fragment) = supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragmentContainer, fragment)
        .commit()
}