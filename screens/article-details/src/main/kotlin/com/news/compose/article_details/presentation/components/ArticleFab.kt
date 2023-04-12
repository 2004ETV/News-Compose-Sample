package com.news.compose.article_details.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.news.compose.article_details.R

@Composable
internal fun ArticleFab(
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.onBackground,
        shape = MaterialTheme.shapes.small,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(0.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = stringResource(R.string.floating_action_button),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}