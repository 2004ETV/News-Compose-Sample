package com.news.compose.feed.presentation.models.mappers

import android.text.format.DateUtils
import com.news.compose.feed.presentation.models.HeadlineItemUi
import com.news.compose.news_api.domain.models.ArticleDomain

internal fun ArticleDomain.asHeadlineItemUI(): HeadlineItemUi {
    val relativeDateString = DateUtils.getRelativeTimeSpanString(
        date.time,
        System.currentTimeMillis(),
        DateUtils.DAY_IN_MILLIS
    )
    return HeadlineItemUi(
        author = author,
        title = title,
        source = source,
        url = url,
        imageUrl = imageUrl,
        date = relativeDateString.toString(),
        description = description,
        content = content
    )
}
