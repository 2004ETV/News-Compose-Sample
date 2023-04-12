package com.news.compose.feed.di

import com.news.compose.core.di.CommonDependencies
import com.news.compose.news_api.data.NewsRepository

interface FeedDependencies : CommonDependencies {
    val newsRepository: NewsRepository
}