package com.evgenykuksov.data.network.interceptors

import com.evgenykuksov.core.language.APP_LANGUAGE
import okhttp3.Interceptor
import okhttp3.Response

internal class LanguageInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        if (!originalHttpUrl.toString().contains(QUERY_LANGUAGE)) {
            val newUrl = originalHttpUrl.newBuilder().addQueryParameter(QUERY_LANGUAGE, APP_LANGUAGE).build()
            request.url(newUrl)
        }
        return chain.proceed(request.build())
    }

    companion object {

        private const val QUERY_LANGUAGE = "language"
    }
}