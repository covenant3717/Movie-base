package com.evgenykuksov.data.di

import android.content.Context
import com.evgenykuksov.data.BuildConfig
import com.evgenykuksov.data.network.interceptors.ApiKeyInterceptor
import com.evgenykuksov.data.network.interceptors.HeaderInterceptor
import com.evgenykuksov.data.network.interceptors.LanguageInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*
// TODO: hilt migration
// как передать парметры: BuildConfig.BASE_URL, BuildConfig.API_VERSION, BuildConfig.API_KEY
internal fun networkModule(baseUrl: String, apiVersion: String, apiKey: String) = module {

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("$baseUrl/$apiVersion/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(OKHTTP_CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(OKHTTP_READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .pingInterval(OKHTTP_PING_INTERVAL_MS, TimeUnit.MILLISECONDS)
            .addInterceptor(ApiKeyInterceptor(apiKey))
            .addInterceptor(LanguageInterceptor())
            .addInterceptor(ChuckInterceptor(this.androidContext()))
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    )
            )
            .build()
    }

    single {
        GsonBuilder().create()
    }
}
*/

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkhttpClient(@ApplicationContext appContext: Context): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(OKHTTP_CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
        .readTimeout(OKHTTP_READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
        .pingInterval(OKHTTP_PING_INTERVAL_MS, TimeUnit.MILLISECONDS)
        .addInterceptor(ApiKeyInterceptor("791455e526b737816137912ea56a5ba4"))
        .addInterceptor(LanguageInterceptor())
        .addInterceptor(ChuckInterceptor(appContext))
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )
        )
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        // TODO: hilt migration
//        .baseUrl("$baseUrl/$apiVersion/")
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

private const val OKHTTP_CONNECT_TIMEOUT_MS = 60_000L
private const val OKHTTP_READ_TIMEOUT_MS = 60_000L
private const val OKHTTP_PING_INTERVAL_MS = 30_000L