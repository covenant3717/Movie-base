package com.evgenykuksov.recipes.di

import com.evgenykuksov.domain.recipes.RecipesUseCase
import org.koin.dsl.module

internal val UseCasesModule = module {

    single { RecipesUseCase(get()) }
}