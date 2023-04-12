package com.news.compose.article_details.presentation

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.news.compose.article_details.R
import com.news.compose.article_details.di.articleDetailsComponent
import com.news.compose.article_details.presentation.components.ArticleContent
import com.news.compose.article_details.presentation.components.ArticleTopBlock
import com.news.compose.core_ui.di.daggerSavedStateViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination(navArgsDelegate = ArticleDetailsNavArgs::class)
@Composable
fun ArticleDetailsScreen(navigation: ArticleDetailsScreenNavigation) {
    val activity = LocalContext.current as Activity

    ArticleDetailsScreen(
        viewModel = daggerSavedStateViewModel {
            articleDetailsComponent.getInstance(activity).articleDetailsViewModelFactory.create(it)
        },
        onBack = navigation::navigateUp
    )

    DisposableEffect(Unit) {
        onDispose {
            articleDetailsComponent.clearInstance()
        }
    }
}

@Composable
private fun ArticleDetailsScreen(
    viewModel: ArticleDetailsViewModel,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ArticleDetailsUiEffect.NavigateBack -> onBack()
                is ArticleDetailsUiEffect.ShareArticle -> {
                    startActivity(
                        context,
                        Intent.createChooser(
                            Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, effect.url)
                                type = "text/plain"
                            },
                            context.getString(R.string.share_the_news)
                        ),
                        null
                    )
                }

                is ArticleDetailsUiEffect.OpenLink -> uriHandler.openUri(effect.url)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            ArticleContent(
                imageUrl = state.imageUrl,
                title = state.title,
                description = state.description.asString(),
                content = state.content.asString(),
                date = state.date,
                source = state.source,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxSize()
            )
        }
        ArticleTopBlock(
            onBack = { viewModel.sendEvent(ArticleDetailsUiEvent.OnBack) },
            onShare = {
                viewModel.sendEvent(
                    ArticleDetailsUiEvent.OnShareArticle(
                        url = state.url,
                        title = state.title
                    )
                )
            },
            onOpenLink = {
                viewModel.sendEvent(ArticleDetailsUiEvent.OnOpenLink(state.url))
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        )
    }
}