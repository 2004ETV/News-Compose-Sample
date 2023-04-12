package com.news.compose.search.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.news.compose.core_ui.R

@Composable
internal fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier,
        value = query,
        onValueChange = { onQueryChange(it) },
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary.copy(0.4F)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search),
                contentDescription = stringResource(com.news.compose.search.R.string.search_icon),
                tint = MaterialTheme.colorScheme.primary.copy(0.4F)
            )
        },
        singleLine = true,
        maxLines = 1,
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.onBackground,
            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        textStyle = TextStyle(fontSize = 16.sp)
    )
}