package com.evgenykuksov.data.network

import com.evgenykuksov.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .addContentTypeHeader()
        return chain.proceed(requestBuilder.build())
    }

    private fun Request.Builder.addContentTypeHeader(): Request.Builder {
        addHeader("Content-Type", "application/json")
        return this
    }
}

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder().addQueryParameter(API_KEY_NAME, API_KEY_VALUE).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }
}

private const val API_KEY_NAME = "apiKey"
private var API_KEY_VALUE = BuildConfig.API_KEY