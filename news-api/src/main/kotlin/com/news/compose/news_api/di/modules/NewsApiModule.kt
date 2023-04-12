package com.news.compose.news_api.di.modules

import com.news.compose.news_api.data.NewsRepository
import com.news.compose.news_api.data.NewsRepositoryImpl
import com.news.compose.news_api.data.headlines.remote.NewsApiDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal interface NewsApiModule {

    companion object {

        @Provides
        fun provideApiDataSource(
            retrofit: Retrofit
        ): NewsApiDataSource = retrofit.create(NewsApiDataSource::class.java)
    }

    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}