package com.news.compose.sample.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.news.compose.core_ui.R
import com.ramcosta.composedestinations.spec.NavGraphSpec

enum class BottomBarDestination(
    val direction: NavGraphSpec,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    Feed(
        direction = NavGraphs.feed,
        icon = R.drawable.news,
        label = R.string.news
    ),
    Search(
        direction = NavGraphs.search,
        icon = R.drawable.search,
        label = R.string.search
    )
}