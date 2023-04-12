package com.news.compose.network.interceptors

import com.news.compose.network.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

internal class NewsApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = BuildConfig.NEWS_API_KEY
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("X-Api-Key", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}
