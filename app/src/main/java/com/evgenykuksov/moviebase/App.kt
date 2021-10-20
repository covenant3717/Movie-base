package com.evgenykuksov.moviebase

import android.app.Application
import com.evgenykuksov.core.di.CoreModules
import com.evgenykuksov.data.di.DataModules
import com.evgenykuksov.moviebase.di.UseCasesModule
import com.evgenykuksov.moviebase.di.ViewModelsModule
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
            modules(CoreModules)
            modules(DataModules)
            modules(UseCasesModule)
            modules(ViewModelsModule)
        }
    }
}

