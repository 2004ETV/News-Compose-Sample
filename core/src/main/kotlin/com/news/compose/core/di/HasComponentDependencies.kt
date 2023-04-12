package com.news.compose.core.di

interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}
