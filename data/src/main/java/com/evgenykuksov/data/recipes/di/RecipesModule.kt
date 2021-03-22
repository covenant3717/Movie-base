package com.evgenykuksov.data.recipes.di

import com.evgenykuksov.data.di.SCHEDULER_IO
import com.evgenykuksov.data.recipes.RecipesRepositoryImpl
import com.evgenykuksov.data.recipes.remote.RemoteRecipes
import com.evgenykuksov.data.recipes.remote.RemoteRecipesImpl
import com.evgenykuksov.data.recipes.remote.api.RecipesApi
import com.evgenykuksov.domain.recipes.RecipesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val RecipesModule = module {

    single {
        get<Retrofit>().create(RecipesApi::class.java)
    }

    single<RemoteRecipes> {
        RemoteRecipesImpl(get())
    }

    single<RecipesRepository> {
        RecipesRepositoryImpl(get(), get(named(SCHEDULER_IO)))
    }
}