package com.news.compose.sample.navigation

import androidx.navigation.NavController
import com.news.compose.article_details.presentation.ArticleDetailsScreenNavigation
import com.news.compose.article_details.presentation.destinations.ArticleDetailsScreenDestination
import com.news.compose.feed.presentation.FeedScreenNavigation
import com.news.compose.feed.presentation.models.HeadlineNavArg
import com.news.compose.sample.navigation.models.mappers.asArticle
import com.news.compose.search.presentation.SearchScreenNavigation
import com.news.compose.search.presentation.models.SearchItemNavArg
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class CommonNavGraphNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController
) : FeedScreenNavigation,
    ArticleDetailsScreenNavigation,
    SearchScreenNavigation {

    override fun openArticleDetails(headlineNavArg: HeadlineNavArg) {
        navController.navigate(ArticleDetailsScreenDestination(article = headlineNavArg.asArticle()) within navGraph)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun openArticleDetails(searchItemNavArg: SearchItemNavArg) {
        navController.navigate(ArticleDetailsScreenDestination(article = searchItemNavArg.asArticle()) within navGraph)
    }
}