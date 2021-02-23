package com.evgenykuksov.data.di

import com.evgenykuksov.data.BuildConfig
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
//            .addConverterFactory(ResponseWrapperConverter())
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(OKHTTP_CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(OKHTTP_READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .pingInterval(OKHTTP_PING_INTERVAL_MS, TimeUnit.MILLISECONDS)
            .addInterceptor(ChuckInterceptor(this.androidContext()))
//            .addInterceptor(HeaderInterceptor(instance()))
//            .addInterceptor(
//                HttpLoggingInterceptor(HttpResponseLogger())
//                    .setLevel(
//                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
//                        else HttpLoggingInterceptor.Level.NONE
//                    )
//            )
            .build()
    }

    single {
        GsonBuilder().create()
    }

    single {
        GsonConverterFactory.create(get())
    }

    single {
        RxJava3CallAdapterFactory.create()
    }

}

private const val BASE_URL = BuildConfig.BASE_URL
private const val OKHTTP_CONNECT_TIMEOUT_MS = 60_000L
private const val OKHTTP_READ_TIMEOUT_MS = 60_000L
private const val OKHTTP_PING_INTERVAL_MS = 30_000L