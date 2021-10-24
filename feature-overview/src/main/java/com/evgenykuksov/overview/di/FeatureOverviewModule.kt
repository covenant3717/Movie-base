package com.evgenykuksov.overview.di

import com.evgenykuksov.core.di.COIL_DEFAULT_LOADER
import com.evgenykuksov.core.di.COIL_GIF_LOADER
import com.evgenykuksov.overview.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val FeatureOverviewModule = module {

    viewModel { OverviewViewModel(get(), get(), get(named(COIL_DEFAULT_LOADER)), get(named(COIL_GIF_LOADER))) }
}