package com.news.compose.search.di

import android.app.Activity
import com.news.compose.core.di.featureComponent
import com.news.compose.core.di.findComponentDependencies
import com.news.compose.core.di.modules.ViewModelModule
import com.news.compose.core.di.scope.FeatureScope
import com.news.compose.core.di.viewmodel.DaggerViewModelFactory
import com.news.compose.search.di.modules.SearchViewModelModule
import dagger.Component

internal val searchComponent = featureComponent<SearchComponent, Activity> { activity ->
    DaggerSearchComponent.factory().create(activity.findComponentDependencies())
}

@FeatureScope
@Component(
    modules = [ViewModelModule::class, SearchViewModelModule::class],
    dependencies = [SearchDependencies::class]
)
internal interface SearchComponent {

    @Component.Factory
    interface Factory {
        fun create(deps: SearchDependencies): SearchComponent
    }

    val viewModelFactory: DaggerViewModelFactory
}