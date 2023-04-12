plugins {
    alias(libs.plugins.android.application)
    id("com.news.compose.module.screen")
    id("com.news.compose.module.dagger")
}

android {
    namespace = "com.news.compose.sample"

    defaultConfig {
        applicationId = "com.news.compose.sample"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        packagingOptions {
            resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }

        testOptions {
            animationsDisabled = true
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {

    implementation(projects.newsApi)
    implementation(projects.network)

    implementation(libs.savedstate)

    implementation(libs.androidx.splashscreen)

    implementation(projects.screens.search)
    implementation(projects.screens.feed)
    implementation(projects.screens.articleDetails)

    implementation(libs.byteBuddy)
    implementation(libs.byteBuddyAndroid)

    implementation(libs.retrofit.client)
    implementation(libs.retrofit.moshi)

    implementation(libs.okhttp.client)
    debugImplementation(libs.okhttp.logginginterceptor)
}