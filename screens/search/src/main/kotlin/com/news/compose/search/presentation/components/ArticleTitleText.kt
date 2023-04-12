package com.news.compose.search.presentation.components

import androidx.annotation.ColorRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun ArticleTitleText(
    title: String,
    modifier: Modifier = Modifier,
    @ColorRes color: Color
) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = color
    )
}