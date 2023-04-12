package com.news.compose.news_api.data

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.news.compose.news_api.data.headlines.remote.NewsApiDataSource
import com.news.compose.news_api.data.headlines.remote.NewsApiDataSource.Companion.DEFAULT_PAGE_SIZE
import com.news.compose.news_api.data.headlines.remote.NewsPagingSource
import com.news.compose.news_api.domain.models.ArticleDomain
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class NewsRepositoryImpl @Inject constructor(
    context: Context,
    private val newsApi: NewsApiDataSource
) : NewsRepository {

    private val country = context.resources.configuration.locales[0].country

    override fun getHeadlinesPagedFlow(): Flow<PagingData<ArticleDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                NewsPagingSource { page, pageSize ->
                    newsApi.getHeadlines(country, page, pageSize)
                }
            }
        ).flow
    }

    override fun getEverythingPagedFlow(searchQuery: String): Flow<PagingData<ArticleDomain>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true),
            pagingSourceFactory = {
                NewsPagingSource { page, pageSize ->
                    newsApi.getEverything(searchQuery, page, pageSize)
                }
            }
        ).flow
    }
}