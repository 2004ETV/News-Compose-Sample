plugins {
    alias(libs.plugins.android.library)
    id("com.news.compose.module.screen")
}

android {
    namespace = "com.news.compose.feed"
}

dependencies {
    implementation(libs.androidx.paging.compose)

    implementation(projects.newsApi)
}