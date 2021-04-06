package com.evgenykuksov.data.data.recipes.di

import com.evgenykuksov.data.data.recipes.RecipesRepositoryImpl
import com.evgenykuksov.data.data.recipes.remote.RemoteRecipes
import com.evgenykuksov.data.data.recipes.remote.RemoteRecipesImpl
import com.evgenykuksov.data.data.recipes.remote.api.RecipesApi
import com.evgenykuksov.domain.recipes.RecipesRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val RecipesModule = module {

    single { get<Retrofit>().create(RecipesApi::class.java) }

    single<RemoteRecipes> { RemoteRecipesImpl(get()) }

    single<RecipesRepository> { RecipesRepositoryImpl(get()) }
}