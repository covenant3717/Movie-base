package com.evgenykuksov.recipes.di

import com.evgenykuksov.recipes.screens.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val ViewModelsModule = module {

    viewModel { MainViewModel(get()) }
}