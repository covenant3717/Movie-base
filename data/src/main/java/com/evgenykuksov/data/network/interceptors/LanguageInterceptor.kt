package com.evgenykuksov.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

internal class LanguageInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder().addQueryParameter(QUERY_LANGUAGE, QUERY_LANGUAGE_VALUE).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }

    companion object {

        private const val QUERY_LANGUAGE = "language"
        private const val QUERY_LANGUAGE_VALUE = "en"
    }
}