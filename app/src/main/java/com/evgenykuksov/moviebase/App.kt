package com.evgenykuksov.moviebase

import android.app.Application
import com.evgenykuksov.actor.di.FeatureActorModule
import com.evgenykuksov.core.di.CoreModules
import com.evgenykuksov.data.di.DataModules
import com.evgenykuksov.main.di.FeatureMainModule
import com.evgenykuksov.movie.di.FeatureMovieModule
import com.evgenykuksov.moviebase.di.UseCasesModule
import com.evgenykuksov.overview.di.FeatureOverviewModule
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
            modules(FeatureMainModule)
            modules(FeatureOverviewModule)
            modules(FeatureMovieModule)
            modules(FeatureActorModule)
        }
    }
}

