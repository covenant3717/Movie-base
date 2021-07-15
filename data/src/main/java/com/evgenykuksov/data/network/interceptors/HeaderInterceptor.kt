package com.evgenykuksov.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().addContentTypeHeader()
        return chain.proceed(requestBuilder.build())
    }

    private fun Request.Builder.addContentTypeHeader(): Request.Builder {
        addHeader("Content-Type", "application/json")
        return this
    }
}