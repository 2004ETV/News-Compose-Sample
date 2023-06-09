package com.news.compose.news_api.di

import com.news.compose.news_api.data.NewsRepository

object NewsApiFactory {

    fun create(deps: NewsApiDependencies): NewsRepository =
        DaggerNewsApiComponent.factory().create(deps).repository
}