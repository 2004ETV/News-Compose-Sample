package com.news.compose.sample.navigation.models.mappers

import com.news.compose.article_details.presentation.Article
import com.news.compose.feed.presentation.models.HeadlineNavArg

fun HeadlineNavArg.asArticle() : Article {
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