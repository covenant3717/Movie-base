package com.evgenykuksov.data.di

import com.evgenykuksov.data.recipes.di.RecipesModule

val DataModule = listOf(RxModule, NetworkModule, RecipesModule)