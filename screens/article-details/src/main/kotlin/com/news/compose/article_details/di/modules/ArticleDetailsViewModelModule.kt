package com.news.compose.article_details.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.compose.core.di.viewmodel.DaggerViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
internal interface ArticleDetailsViewModelModule {

    companion object {

        @Provides
        fun provideArticleDetailsViewModelFactory(
            creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory = DaggerViewModelFactory(creators)
    }
}
