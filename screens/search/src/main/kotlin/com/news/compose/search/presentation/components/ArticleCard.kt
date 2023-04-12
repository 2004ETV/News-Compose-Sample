package com.news.compose.search.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.news.compose.core_ui.components.ImageWithPlaceholder
import com.news.compose.search.presentation.models.SearchItemUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArticleCard(
    article: SearchItemUi,
    modifier: Modifier = Modifier,
    openArticle: (SearchItemUi) -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = { openArticle(article) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxHeight(),
        ) {
            if (article.imageUrl != null) {
                ImageWithPlaceholder(
                    imageUrl = article.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.small)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            ArticleSourceWithDateChip(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                ),
                date = article.date,
                source = article.source
            )
            ArticleTitleText(
                modifier = Modifier.padding(top = 8.dp),
                title = article.title,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}