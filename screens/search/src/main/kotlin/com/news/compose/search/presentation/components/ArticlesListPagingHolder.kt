package com.news.compose.search.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.news.compose.core_ui.R
import com.news.compose.core_ui.components.ErrorMessageWithButton
import com.news.compose.news_api.data.headlines.remote.models.NewsLoadException
import com.news.compose.search.presentation.models.SearchItemUi

@Composable
internal fun ArticlesListPagingHolder(
    articlesItems: LazyPagingItems<SearchItemUi>,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    openArticle: (SearchItemUi) -> Unit,
    onRetry: () -> Unit
) {
    when (val refresh = articlesItems.loadState.refresh) {
        LoadState.Loading -> {
            Box(modifier = modifier) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is LoadState.NotLoading -> {
            if (articlesItems.itemCount != 0) {
                ArticlesList(
                    articles = articlesItems,
                    listState = listState,
                    openArticle = { openArticle(it) },
                    onRetry = onRetry
                )
            } else {
                EmptySearch(modifier = modifier)
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
