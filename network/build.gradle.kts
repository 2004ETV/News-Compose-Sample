import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    id("com.news.compose.module.dagger")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.news.compose.network"

    val apiKey = gradleLocalProperties(project.rootDir).getProperty("apiKey")

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "NEWS_API_KEY", apiKey)
            buildConfigField("String", "NEWS_URL", "\"https://newsapi.org/v2/\"")
        }
        getByName("release") {
            buildConfigField("String", "NEWS_API_KEY", apiKey)
            buildConfigField("String", "NEWS_URL", "\"https://newsapi.org/v2/\"")
        }
    }
}

dependencies {
    implementation(projects.core)

    implementation(libs.retrofit.client)
    implementation(libs.retrofit.moshi)

    implementation(libs.okhttp.client)
    debugImplementation(libs.okhttp.logginginterceptor)

    implementation(libs.androidx.core.ktx)

    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)
    ksp(libs.moshi.compiler)
}