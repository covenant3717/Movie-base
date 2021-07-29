package com.evgenykuksov.data.network.interceptors

import com.evgenykuksov.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder().addQueryParameter(API_KEY_NAME, API_KEY_VALUE).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }
}

private const val API_KEY_NAME = "api_key"
private var API_KEY_VALUE = BuildConfig.API_KEY