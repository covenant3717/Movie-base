package com.evgenykuksov.recipes

import android.app.Application
import com.evgenykuksov.data.di.DataModule
import com.evgenykuksov.recipes.di.PresentationModule
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
            modules(DataModule)
            modules(PresentationModule)
        }
    }
}

