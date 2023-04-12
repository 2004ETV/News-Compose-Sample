plugins {
    alias(libs.plugins.android.library)
    id("com.news.compose.module.compose")
}

android {
    namespace = "com.news.compose.core_ui"
}

dependencies {
    implementation(projects.core)

    implementation(libs.androidx.paging.compose)
}