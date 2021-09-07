package com.evgenykuksov.moviebase.di

import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.evgenykuksov.moviebase.R
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val CoilModule = module {

    single<ImageLoader>(named(COIL_DEFAULT_LOADER)) {
        ImageLoader(this.androidContext())
            .newBuilder()
            .crossfade(300)
            .placeholder(R.drawable.ic_common_popcorn)
            .error(R.drawable.ic_common_popcorn)
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

const val COIL_DEFAULT_LOADER = "coil_default_loader"
const val COIL_GIF_LOADER = "coil_gif_loader"