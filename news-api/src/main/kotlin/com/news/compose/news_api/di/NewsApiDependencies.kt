package com.news.compose.news_api.di

import com.news.compose.core.di.CommonDependencies
import retrofit2.Retrofit

interface NewsApiDependencies : CommonDependencies {
    fun getRetrofit(): Retrofit
}