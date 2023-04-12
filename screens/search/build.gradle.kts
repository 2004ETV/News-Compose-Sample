plugins {
    alias(libs.plugins.android.library)
    id("com.news.compose.module.screen")
}

android {
    namespace = "com.news.compose.search"
}

dependencies {
    implementation(projects.newsApi)

    implementation(libs.androidx.paging.compose)
}