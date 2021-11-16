package com.evgenykuksov.home.di

import com.evgenykuksov.core.di.COIL_DEFAULT_LOADER
import com.evgenykuksov.core.di.COIL_GIF_LOADER
import com.evgenykuksov.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureHomeModule = module {

    viewModel {
        HomeViewModel(
            get(),
            get(),
            get(),
            get(named(COIL_DEFAULT_LOADER)),
            get(named(COIL_GIF_LOADER)),
        )
    }
}