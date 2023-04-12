package com.news.compose.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.news.compose.news_api.data.NewsRepository
import com.news.compose.news_api.domain.models.ArticleDomain
import com.news.compose.search.presentation.models.mappers.asSearchItemUI
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SearchUiEffect>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnRetry -> {
                _state.update { it.copy(isRefreshing = true) }
                loadSearchResult(_state.value.searchQuery)
            }

            is SearchUiEvent.OnSearchType -> {
                _state.update { it.copy(isLoading = true, searchQuery = event.query) }
                loadSearchResult(event.query)
            }

            is SearchUiEvent.OnNews -> {
                viewModelScope.launch {
                    _effect.emit(SearchUiEffect.NavigateToArticle(event.article))
                }
            }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun loadSearchResult(query: String) {
        val result = newsRepository.getEverythingPagedFlow(query)
            .debounce(timeoutMillis = 300L)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .mapLatest { data -> data.map(ArticleDomain::asSearchItemUI) }
        _state.update { it.copy(isLoading = false, isRefreshing = false, searchResult = result) }
    }
}