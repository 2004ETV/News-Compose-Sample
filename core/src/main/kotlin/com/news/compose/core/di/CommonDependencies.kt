package com.news.compose.core.di

import android.content.Context

interface CommonDependencies: ComponentDependencies {
    val context: Context
}
