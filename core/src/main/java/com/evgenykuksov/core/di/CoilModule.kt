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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoilModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class EmptyLoaderCoil

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DefaultLoaderCoil

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GiftLoaderCoil

    @EmptyLoaderCoil
    @Singleton
    @Provides
    fun provideEmptyLoader(@ApplicationContext appContext: Context): ImageLoader = ImageLoader(appContext)
        .newBuilder()
        .error(R.drawable.ic_core_popcorn)
        .build()

    @DefaultLoaderCoil
    @Singleton
    @Provides
    fun provideDefaultLoader(@ApplicationContext appContext: Context): ImageLoader = ImageLoader(appContext)
        .newBuilder()
        .placeholder(R.drawable.ic_core_popcorn)
        .error(R.drawable.ic_core_popcorn)
        .build()

    @GiftLoaderCoil
    @Singleton
    @Provides
    fun provideGifLoader(@ApplicationContext appContext: Context): ImageLoader = ImageLoader.Builder(appContext)
        .componentRegistry {
            if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder(appContext))
            else add(GifDecoder())
        }
        .build()
}