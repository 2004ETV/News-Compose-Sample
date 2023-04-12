package com.news.compose.article_details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.news.compose.core_ui.R

@Composable
internal fun ArticleTopBlock(
    onBack: () -> Unit,
    onShare: () -> Unit,
    onOpenLink: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ArticleFab(
            icon = R.drawable.chevron_left,
            onClick = { onBack() }
        )
        Row {
            ArticleFab(
                icon = R.drawable.external_link,
                onClick = { onOpenLink() },
                modifier = Modifier.padding(end = 8.dp)
            )
            ArticleFab(
                icon = R.drawable.share,
                onClick = { onShare() }
            )
        }
    }
}