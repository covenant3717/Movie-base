package com.evgenykuksov.recipes.screens.main

import android.os.Bundle
import com.evgenykuksov.recipes.R
import com.evgenykuksov.recipes.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getRecipes()
    }

}