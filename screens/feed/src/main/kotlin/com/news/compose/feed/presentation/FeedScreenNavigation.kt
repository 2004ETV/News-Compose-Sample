package com.news.compose.feed.presentation

import com.news.compose.feed.presentation.models.HeadlineNavArg

interface FeedScreenNavigation {

    fun openArticleDetails(headlineNavArg: HeadlineNavArg)
}