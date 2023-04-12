package com.news.compose.article_details.presentation

import androidx.compose.runtime.Immutable
import com.news.compose.article_details.R
import com.news.compose.core_ui.UiText

@Immutable
internal data class ArticleDetailsUiState(
    val title: String = "",
    val source: String = "",
    val url: String = "",
    val imageUrl: String? = null,
    val date: String = "",
    val description: UiText = UiText.StringResource(R.string.no_description),
    val content: UiText = UiText.StringResource(R.string.no_content)
) {
    companion object {
        val Empty = ArticleDetailsUiState()
    }
}

internal sealed class ArticleDetailsUiEvent {
    object OnBack : ArticleDetailsUiEvent()
    class OnShareArticle(val url: String, val title: String): ArticleDetailsUiEvent()
    class OnOpenLink(val url: String): ArticleDetailsUiEvent()
}

internal sealed class ArticleDetailsUiEffect {
    object NavigateBack : ArticleDetailsUiEffect()
    class OpenLink(val url: String): ArticleDetailsUiEffect()
    class ShareArticle(val url: String, val title: String): ArticleDetailsUiEffect()
}