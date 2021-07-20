package com.evgenykuksov.moviebase.di

import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val CoilModule = module {

    single(named(COIL_GIF_LOADER)) {
        ImageLoader.Builder(this.androidContext())
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder(this@single.androidContext()))
                else add(GifDecoder())
            }
            .build()
    }
}

const val COIL_GIF_LOADER = "coil_gif_loader"