package com.news.compose.article_details.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ArticleDetailsNavArgs(val article: Article)

@Parcelize
data class Article(
    val author: String?,
    val title: String,
    val source: String,
    val url: String,
    val imageUrl: String?,
    val date: String,
    val description: String?,
    val content: String?
) : Parcelable