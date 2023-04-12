package com.news.compose.feed.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.news.compose.core_ui.R
import com.news.compose.core_ui.components.ErrorMessageWithButton
import com.news.compose.feed.presentation.models.HeadlineItemUi
import com.news.compose.news_api.data.headlines.remote.models.NewsLoadException

@Composable
internal fun FeedListPagingHolder(
    feedItems: LazyPagingItems<HeadlineItemUi>,
    modifier: Modifier = Modifier,
    openArticle: (HeadlineItemUi) -> Unit,
    onRetry: () -> Unit
) {
    when (val refresh = feedItems.loadState.refresh) {
        LoadState.Loading -> {
            Box(modifier = modifier) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is LoadState.NotLoading -> {
            if (feedItems.itemCount != 0) {
                FeedList(
                    feedItems = feedItems,
                    openArticle = { openArticle(it) },
                    onRetry = onRetry
                )
            }
        }

        is LoadState.Error -> {
            val apiMessage = (refresh.error as? NewsLoadException)?.reason?.message
            ErrorMessageWithButton(
                message = apiMessage ?: stringResource(R.string.unknown_error),
                modifier = modifier
            ) {
                onRetry()
            }
        }
    }
}