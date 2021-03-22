package com.evgenykuksov.recipes.di

import com.evgenykuksov.recipes.screens.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ViewModelModule = module {

    viewModel { MainViewModel(get(), get(named(SCHEDULER_UI))) }
}