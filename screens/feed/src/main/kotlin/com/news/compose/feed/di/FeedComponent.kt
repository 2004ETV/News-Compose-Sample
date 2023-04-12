package com.news.compose.feed.di

import android.app.Activity
import com.news.compose.core.di.featureComponent
import com.news.compose.core.di.findComponentDependencies
import com.news.compose.core.di.modules.ViewModelModule
import com.news.compose.core.di.viewmodel.DaggerViewModelFactory
import com.news.compose.feed.di.modules.FeedViewModelModule
import dagger.Component

internal val feedComponent = featureComponent<FeedComponent, Activity> { activity ->
    DaggerFeedComponent.factory().create(activity.findComponentDependencies())
}

@Component(
    modules = [ViewModelModule::class, FeedViewModelModule::class],
    dependencies = [FeedDependencies::class]
)
internal interface FeedComponent {

    @Component.Factory
    interface Factory {
        fun create(deps: FeedDependencies): FeedComponent
    }

    val viewModelFactory: DaggerViewModelFactory
}