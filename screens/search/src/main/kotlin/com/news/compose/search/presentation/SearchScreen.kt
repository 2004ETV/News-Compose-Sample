package com.news.compose.search.presentation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.news.compose.core_ui.di.daggerViewModel
import com.news.compose.search.di.searchComponent
import com.news.compose.search.presentation.components.ArticlesListPagingHolder
import com.news.compose.search.presentation.components.SearchField
import com.news.compose.search.presentation.models.SearchItemNavArg
import com.news.compose.search.presentation.models.asNavArg
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@Destination
@Composable
fun SearchScreen(navigation: SearchScreenNavigation) {
    val activity = LocalContext.current as Activity

    SearchScreen(
        viewModel = daggerViewModel(factory = searchComponent.getInstance(activity).viewModelFactory),
        openArticle = navigation::openArticleDetails
    )
}

@Composable
private fun SearchScreen(
    viewModel: SearchViewModel,
    openArticle: (SearchItemNavArg) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val articlesItems = state.searchResult.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(listState.isScrollInProgress) {
        focusManager.clearFocus()
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SearchUiEffect.NavigateToArticle -> openArticle(effect.article)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
            onRefresh = { viewModel.sendEvent(SearchUiEvent.OnRetry) },
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            ArticlesListPagingHolder(
                articlesItems = articlesItems,
                listState = listState,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxSize(),
                openArticle = { viewModel.sendEvent(SearchUiEvent.OnNews(it.asNavArg())) },
                onRetry = { articlesItems.retry() }
            )
            SearchField(
                query = state.searchQuery,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 14.dp)
                    .padding(horizontal = 16.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
                onQueryChange = {
                    viewModel.sendEvent(SearchUiEvent.OnSearchType(it))
                    coroutineScope.launch {
                        listState.scrollToItem(0, 0)
                    }
                },
            )
        }
    }
}