package com.news.compose.sample.di.modules

import com.news.compose.article_details.di.ArticleDetailsDependencies
import com.news.compose.core.di.ComponentDependencies
import com.news.compose.core.di.ComponentDependenciesKey
import com.news.compose.feed.di.FeedDependencies
import com.news.compose.news_api.di.NewsApiDependencies
import com.news.compose.news_api.di.NewsApiFactory
import com.news.compose.sample.di.AppComponent
import com.news.compose.search.di.SearchDependencies
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface ComponentDependenciesModule {

    companion object {

        @Provides
        fun provideNewsApi(appComponent: AppComponent) = NewsApiFactory.create(appComponent)
    }

    @Binds
    @IntoMap
    @ComponentDependenciesKey(FeedDependencies::class)
    fun bindFeedDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(NewsApiDependencies::class)
    fun bindNewsApiDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ArticleDetailsDependencies::class)
    fun bindArticleDetailsDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SearchDependencies::class)
    fun bindSearchDependencies(appComponent: AppComponent): ComponentDependencies
}