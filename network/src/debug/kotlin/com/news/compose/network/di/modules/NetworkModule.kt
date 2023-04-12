package com.news.compose.network.di.modules

import com.news.compose.core.di.scope.AppScope
import com.news.compose.network.BuildConfig
import com.news.compose.network.EitherNewsAdapterFactory
import com.news.compose.network.interceptors.NewsApiKeyInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.addAdapter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
interface NetworkModule {

    companion object {

        @OptIn(ExperimentalStdlibApi::class)
        @Provides
        fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .addAdapter(Rfc3339DateJsonAdapter().nullSafe())
                .build()
        }

        @Provides
        @IntoSet
        fun provideNewsApiKeyInterceptor(): Interceptor = NewsApiKeyInterceptor()

        @Provides
        @IntoSet
        fun provideLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        @Provides
        @AppScope
        fun provideOkHttpClient(
            interceptors: Set<@JvmSuppressWildcards Interceptor>
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .apply { interceptors.forEach(::addInterceptor) }
                .callTimeout(5, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        @AppScope
        fun provideRetrofit(
            client: OkHttpClient,
            moshi: Moshi
        ): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.NEWS_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(EitherNewsAdapterFactory())
                .build()
        }
    }
}