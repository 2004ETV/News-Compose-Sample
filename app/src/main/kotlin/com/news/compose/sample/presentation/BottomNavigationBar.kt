package com.news.compose.sample.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.news.compose.sample.R
import com.news.compose.sample.navigation.BottomBarDestination
import com.ramcosta.composedestinations.spec.NavGraphSpec

@Composable
fun BottomNavigationBar(
    selectedNavigation: NavGraphSpec,
    onNavigationSelected: (NavGraphSpec) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.onBackground,
        tonalElevation = 0.dp
    ) {
        BottomBarDestination.values().forEach { destination ->
            NavigationBarItem(
                selected = selectedNavigation == destination.direction,
                onClick = { onNavigationSelected(destination.direction) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(destination.icon),
                        contentDescription = stringResource(R.string.bottom_navigation_icon)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.label),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary.copy(0.4F),
                    unselectedTextColor = MaterialTheme.colorScheme.primary.copy(0.4F),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}