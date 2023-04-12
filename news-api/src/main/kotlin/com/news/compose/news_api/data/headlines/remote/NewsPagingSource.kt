package com.news.compose.news_api.data.headlines.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.news.compose.core.entity.Either
import com.news.compose.core.entity.ErrorReason
import com.news.compose.core.entity.unpack
import com.news.compose.news_api.data.headlines.remote.NewsApiDataSource.Companion.DEFAULT_PAGE_SIZE
import com.news.compose.news_api.data.headlines.remote.NewsApiDataSource.Companion.START_PAGE
import com.news.compose.news_api.data.headlines.remote.models.ArticleResponse
import com.news.compose.news_api.data.headlines.remote.models.NewsLoadException
import com.news.compose.news_api.data.headlines.remote.models.NewsPageResponse
import com.news.compose.news_api.data.headlines.remote.models.mappers.toArticle
import com.news.compose.news_api.domain.models.ArticleDomain
import javax.inject.Inject

internal class NewsPagingSource @Inject constructor(
    private val loadPage: suspend (page: Int, pageSize: Int) -> Either<ErrorReason, NewsPageResponse>
) : PagingSource<Int, ArticleDomain>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomain> {
        val page = params.key ?: START_PAGE
        return loadPage(page, DEFAULT_PAGE_SIZE).unpack(
            success = { news ->
                LoadResult.Page(
                    data = news.articles.map(ArticleResponse::toArticle),
                    prevKey = (page - 1).takeIf { it >= 1 },
                    nextKey = (page + 1).takeIf { news.articles.size == DEFAULT_PAGE_SIZE },
                )
            },
            error = { reason ->
                LoadResult.Error(NewsLoadException(reason))
            }
        )
    }
}