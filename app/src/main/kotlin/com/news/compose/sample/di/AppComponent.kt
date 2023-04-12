package com.news.compose.sample.di

import android.app.Application
import com.news.compose.article_details.di.ArticleDetailsDependencies
import com.news.compose.core.di.CommonDependencies
import com.news.compose.core.di.scope.AppScope
import com.news.compose.feed.di.FeedDependencies
import com.news.compose.network.di.modules.NetworkModule
import com.news.compose.news_api.di.NewsApiDependencies
import com.news.compose.sample.MainActivity
import com.news.compose.sample.NewsComposeApp
import com.news.compose.sample.di.modules.AppModule
import com.news.compose.sample.di.modules.ComponentDependenciesModule
import com.news.compose.search.di.SearchDependencies
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        AppModule::class,
        ComponentDependenciesModule::class,
        NetworkModule::class
    ]
)
interface AppComponent :
    CommonDependencies,
    FeedDependencies,
    NewsApiDependencies,
    ArticleDetailsDependencies,
    SearchDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(application: NewsComposeApp)
    fun inject(mainActivity: MainActivity)
}