package com.news.compose.search.presentation.models.mappers

import android.text.format.DateUtils
import com.news.compose.news_api.domain.models.ArticleDomain
import com.news.compose.search.presentation.models.SearchItemUi

internal fun ArticleDomain.asSearchItemUI(): SearchItemUi {
    val relativeDateString = DateUtils.getRelativeTimeSpanString(
        date.time,
        System.currentTimeMillis(),
        DateUtils.DAY_IN_MILLIS
    )
    return SearchItemUi(
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
