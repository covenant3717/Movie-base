package com.evgenykuksov.moviebase

import android.app.Application
import com.evgenykuksov.actor.di.featureActorModule
import com.evgenykuksov.core.di.coreModules
import com.evgenykuksov.data.di.dataModules
import com.evgenykuksov.moviebase.di.useCasesModule
//import com.evgenykuksov.home.di.featureHomeModule
import com.evgenykuksov.moviebase.di.navigationModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
internal class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(navigationModule)
            modules(coreModules)
            modules(dataModules(BuildConfig.BASE_URL, BuildConfig.API_VERSION, BuildConfig.API_KEY))
            modules(useCasesModule)
//            modules(featureHomeModule)
            modules(featureActorModule)
        }
    }
}

