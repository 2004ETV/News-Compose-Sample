package com.news.compose.article_details.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.news.compose.article_details.R

@Composable
internal fun BackButton(
    modifier: Modifier,
    onBack: () -> Unit
) {
    IconButton(
        onClick = { onBack() },
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.onBackground)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.back_nav_icon),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}
