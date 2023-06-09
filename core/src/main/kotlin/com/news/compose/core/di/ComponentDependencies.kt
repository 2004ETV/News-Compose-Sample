package com.news.compose.core.di

import android.app.Activity
import android.app.Service

interface ComponentDependencies

typealias ComponentDependenciesProvider =
        Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

fun Activity.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    val hasDaggerProviders = when (application) {
        is HasComponentDependencies -> application as HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

fun Service.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    val hasDaggerProviders = when (application) {
        is HasComponentDependencies -> application as HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

inline fun <reified T : ComponentDependencies> Activity.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

inline fun <reified T : ComponentDependencies> Service.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}
