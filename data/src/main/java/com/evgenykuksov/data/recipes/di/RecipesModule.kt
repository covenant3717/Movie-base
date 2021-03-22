package com.evgenykuksov.data.recipes.di

import com.evgenykuksov.data.recipes.remote.RemoteRecipes
import com.evgenykuksov.data.recipes.remote.RemoteRecipesImpl
import com.evgenykuksov.data.recipes.remote.api.RecipesApi
import org.koin.dsl.module
import retrofit2.Retrofit

val RecipesModule = module {

    single {
        get<Retrofit>().create(RecipesApi::class.java)
    }

    single<RemoteRecipes> {
        RemoteRecipesImpl(get<RecipesApi>())
    }
}