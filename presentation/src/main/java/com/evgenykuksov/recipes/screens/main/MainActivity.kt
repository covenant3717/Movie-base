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

        viewModel.setIntent(MainContract.Intent.Start)
    }

    override fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    is MainContract.State.Idle -> {
                        pb.isVisible = false
                    }
                    is MainContract.State.Loading -> {
                        pb.isVisible = true
                    }
                    is MainContract.State.Success -> {
                        pb.isVisible = false
                        tvSize.text = "size: $it."
                    }
                }
            }

            viewModel.singleEffect.collect {
                when (it) {
                    is MainContract.Effect.ShowToast -> {
                        Toast.makeText(this@MainActivity, "test", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}