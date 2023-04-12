plugins {
    alias(libs.plugins.android.library)
    id("com.news.compose.module.dagger")
}

android {
    namespace = "com.news.compose.core"
}

dependencies {
    implementation(libs.savedstate)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
}