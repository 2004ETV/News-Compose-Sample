package com.news.compose.feed.presentation

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.news.compose.core_ui.di.daggerViewModel
import com.news.compose.feed.di.feedComponent
import com.news.compose.feed.presentation.components.FeedListPagingHolder
import com.news.compose.feed.presentation.models.HeadlineNavArg
import com.news.compose.feed.presentation.models.asNavArg
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun FeedScreen(navigation: FeedScreenNavigation) {
    val activity = LocalContext.current as Activity

    FeedScreen(
        viewModel = daggerViewModel(factory = feedComponent.getInstance(activity).viewModelFactory),
        openArticle = navigation::openArticleDetails
    )
}

@Composable
private fun FeedScreen(
    viewModel: FeedViewModel,
    openArticle: (HeadlineNavArg) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val feedItems = state.feed.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is FeedUiEffect.NavigateToArticle -> openArticle(effect.article)
            }
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
        onRefresh = { viewModel.sendEvent(FeedUiEvent.OnRetry) },
        modifier = Modifier.fillMaxSize(),

    ) {
        FeedListPagingHolder(
            modifier = Modifier.fillMaxSize(),
            feedItems = feedItems,
            openArticle = { viewModel.sendEvent(FeedUiEvent.OnNavigateToArticle(it.asNavArg())) },
            onRetry = { feedItems.retry() }
        )
    }
}