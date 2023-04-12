package com.news.compose.sample.navigation.models.mappers

import com.news.compose.article_details.presentation.Article
import com.news.compose.search.presentation.models.SearchItemNavArg

fun SearchItemNavArg.asArticle(): Article {
    return Article(
        author = author,
        title = title,
        source = source,
        url = url,
        imageUrl= imageUrl,
        date = date,
        description = description,
        content = content
    )
}
