package com.news.compose.news_api.data

import androidx.paging.PagingData
import com.news.compose.news_api.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getHeadlinesPagedFlow(): Flow<PagingData<ArticleDomain>>

    fun getEverythingPagedFlow(searchQuery: String): Flow<PagingData<ArticleDomain>>
}