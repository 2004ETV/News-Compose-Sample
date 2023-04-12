package com.news.compose.feed.presentation

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.news.compose.feed.presentation.models.HeadlineItemUi
import com.news.compose.feed.presentation.models.HeadlineNavArg
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
internal data class FeedUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val feed: Flow<PagingData<HeadlineItemUi>> = emptyFlow()
) {
    companion object {
        val Empty = FeedUiState()
    }
}

internal sealed class FeedUiEvent {
    object OnRetry : FeedUiEvent()
    class OnNavigateToArticle(val article: HeadlineNavArg) : FeedUiEvent()
}

internal sealed class FeedUiEffect {
    class NavigateToArticle(val article: HeadlineNavArg) : FeedUiEffect()
}