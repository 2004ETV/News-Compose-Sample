package com.news.compose.sample.di.modules

import android.app.Application
import android.content.Context
import com.news.compose.core.di.InjectedKey
import com.news.compose.core.di.scope.AppScope
import com.news.compose.sample.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
interface AppModule {

    companion object {

        @AppScope
        @Provides
        fun provideContext(app: Application): Context = app.applicationContext

        @JvmStatic
        @Provides
        @Named(InjectedKey.Configuration.VERSION_NAME)
        fun provideAppVersionName(): String = BuildConfig.VERSION_NAME
    }
}