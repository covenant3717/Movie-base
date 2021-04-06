package com.evgenykuksov.recipes.screens.main

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.evgenykuksov.recipes.R
import com.evgenykuksov.recipes.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeState()
        observeSingleEffect()
        viewModel.setIntent(MainContract.Intent.Start)
    }

    private fun observeState() = lifecycleScope.launchWhenStarted {
        viewModel.state.collect {
            when (it) {
                is MainContract.State.Idle -> {
                    pb.isVisible = false
                    tvSize.text = "size:"
                }
                is MainContract.State.Loading -> {
                    pb.isVisible = true
                    tvSize.text = "size:"
                }
                is MainContract.State.Success -> {
                    pb.isVisible = false
                    tvSize.text = "size: ${it.list.size}"
                }
            }
        }
    }

    private fun observeSingleEffect() = lifecycleScope.launchWhenStarted {
        viewModel.singleEffect.collect {
            when (it) {
                is MainContract.Effect.ToastError -> {
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}