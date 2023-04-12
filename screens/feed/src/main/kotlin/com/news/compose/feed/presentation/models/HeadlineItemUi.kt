package com.news.compose.feed.presentation.models

internal class HeadlineItemUi(
    val author: String?,
    val title: String,
    val source: String,
    val url: String,
    val imageUrl: String?,
    val date: String,
    val description: String?,
    val content: String?
)

internal fun HeadlineItemUi.asNavArg(): HeadlineNavArg {
    return HeadlineNavArg(author, title, source, url, imageUrl, date, description, content)
}