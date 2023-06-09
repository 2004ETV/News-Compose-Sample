package com.news.compose.search.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.compose.core.di.viewmodel.DaggerViewModelFactory
import com.news.compose.core.di.viewmodel.ViewModelKey
import com.news.compose.search.presentation.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
internal interface SearchViewModelModule {

    companion object {

        @Provides
        fun provideSearchViewModelFactory(
            creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory = DaggerViewModelFactory(creators)
    }

    @Binds
    @ViewModelKey(SearchViewModel::class)
    @IntoMap
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}