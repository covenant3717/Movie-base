package com.evgenykuksov.core.di

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.evgenykuksov.core.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import javax.inject.Singleton

internal val coilModule = module {

    single<ImageLoader>(named(COIL_EMPTY_LOADER)) {
        ImageLoader(this.androidContext())
            .newBuilder()
            .error(R.drawable.ic_core_popcorn)
            .build()
    }

    single<ImageLoader>(named(COIL_DEFAULT_LOADER)) {
        ImageLoader(this.androidContext())
            .newBuilder()
            .placeholder(R.drawable.ic_core_popcorn)
            .error(R.drawable.ic_core_popcorn)
            .build()
    }

    single<ImageLoader>(named(COIL_GIF_LOADER)) {
        ImageLoader.Builder(this.androidContext())
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder(this@single.androidContext()))
                else add(GifDecoder())
            }
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object CoilModule {

    @Singleton
    @Provides
    fun provideImageLoader(@ApplicationContext appContext: Context): ImageLoader = ImageLoader(appContext)
        .newBuilder()
        .error(R.drawable.ic_core_popcorn)
        .build()
}

const val COIL_EMPTY_LOADER = "coil_empty_loader"
const val COIL_DEFAULT_LOADER = "coil_default_loader"
const val COIL_GIF_LOADER = "coil_gif_loader"