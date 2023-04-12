package com.news.compose.news_api.data.headlines.remote.models.mappers

import com.news.compose.news_api.data.headlines.remote.models.ArticleResponse
import com.news.compose.news_api.domain.models.ArticleDomain

internal fun ArticleResponse.toArticle(): ArticleDomain {
    return ArticleDomain(
        author = author,
        title = title,
        description = description,
        source = source.name,
        url = url,
        imageUrl = imageUrl,
        date = date,
        content = content
    )
}
