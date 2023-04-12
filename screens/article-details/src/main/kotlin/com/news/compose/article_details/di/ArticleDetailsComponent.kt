package com.news.compose.article_details.di

import android.app.Activity
import com.news.compose.article_details.di.modules.ArticleDetailsViewModelModule
import com.news.compose.article_details.presentation.ArticleDetailsViewModel
import com.news.compose.core.di.featureComponent
import com.news.compose.core.di.findComponentDependencies
import com.news.compose.core.di.modules.ViewModelModule
import com.news.compose.core.di.scope.FeatureScope
import dagger.Component

internal val articleDetailsComponent = featureComponent<ArticleDetailsComponent, Activity> { activity ->
    DaggerArticleDetailsComponent.factory().create(activity.findComponentDependencies())
}

@FeatureScope
@Component(
    modules = [ViewModelModule::class, ArticleDetailsViewModelModule::class],
    dependencies = [ArticleDetailsDependencies::class]
)
internal interface ArticleDetailsComponent {

    @Component.Factory
    interface Factory {
        fun create(deps: ArticleDetailsDependencies): ArticleDetailsComponent
    }

    val articleDetailsViewModelFactory: ArticleDetailsViewModel.Factory
}