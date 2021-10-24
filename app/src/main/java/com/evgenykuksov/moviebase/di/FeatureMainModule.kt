package com.evgenykuksov.moviebase.di

import com.evgenykuksov.moviebase.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FeatureMainModule = module {

    viewModel { MainViewModel() }
}