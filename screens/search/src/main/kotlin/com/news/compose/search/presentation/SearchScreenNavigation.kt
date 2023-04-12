package com.news.compose.search.presentation

import com.news.compose.search.presentation.models.SearchItemNavArg

interface SearchScreenNavigation {

    fun openArticleDetails(searchItemNavArg: SearchItemNavArg)
}
