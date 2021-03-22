package com.evgenykuksov.recipes

import android.app.Application
import com.evgenykuksov.data.di.NetworkModule
import com.evgenykuksov.data.di.RxDataModule
import com.evgenykuksov.data.recipes.di.RecipesModule
import com.evgenykuksov.recipes.di.RxPresentationModule
import com.evgenykuksov.recipes.di.UseCasesModule
import com.evgenykuksov.recipes.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                ViewModelModule,
                UseCasesModule,
                RxPresentationModule,
                NetworkModule,
                RxDataModule,
                RecipesModule
            )
        }
    }
}

