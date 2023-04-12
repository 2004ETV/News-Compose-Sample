plugins {
    alias(libs.plugins.android.library)
    id("com.news.compose.module.dagger")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.news.compose.news_api"
}

dependencies {
    implementation(projects.core)
    implementation(projects.network)

    implementation(libs.androidx.paging.runtime.ktx)

    implementation(libs.retrofit.client)

    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.compiler)
}