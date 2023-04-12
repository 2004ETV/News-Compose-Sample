package com.news.compose.sample

import android.app.Application
import com.news.compose.core.di.ComponentDependenciesProvider
import com.news.compose.core.di.HasComponentDependencies
import com.news.compose.sample.di.DaggerAppComponent
import javax.inject.Inject

open class NewsComposeApp : Application(), HasComponentDependencies {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory()
            .create(this)
            .inject(this)
    }
}