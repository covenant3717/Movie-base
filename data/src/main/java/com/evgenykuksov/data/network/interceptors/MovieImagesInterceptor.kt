package com.evgenykuksov.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

internal class MovieImagesInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        if (originalHttpUrl.toString().contains(QUERY_MOVIE_WORD) && originalHttpUrl.toString().contains(QUERY_IMAGES_WORD)) {
            val newUrl = originalHttpUrl.newBuilder().removeAllQueryParameters(QUERY_LANGUAGE).build()
            request.url(newUrl)
        }
        return chain.proceed(request.build())
    }

    companion object {

        private const val QUERY_MOVIE_WORD = "movie/"
        private const val QUERY_IMAGES_WORD = "/images"
        private const val QUERY_LANGUAGE = "language"
    }
}