package com.news.compose.news_api.di

import com.news.compose.news_api.data.NewsRepository
import com.news.compose.news_api.di.modules.NewsApiModule
import dagger.Component

@Component(
    modules = [NewsApiModule::class],
    dependencies = [NewsApiDependencies::class]
)
interface NewsApiComponent {

    @Component.Factory
    interface Factory {
        fun create(deps: NewsApiDependencies): NewsApiComponent
    }

    val repository: NewsRepository
}