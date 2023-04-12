package com.news.compose.search.di

import com.news.compose.core.di.CommonDependencies
import com.news.compose.news_api.data.NewsRepository

interface SearchDependencies : CommonDependencies {
    val newsRepository: NewsRepository
}