package com.evgenykuksov.main.di

import com.evgenykuksov.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FeatureMainModule = module {

    viewModel { MainViewModel() }
}