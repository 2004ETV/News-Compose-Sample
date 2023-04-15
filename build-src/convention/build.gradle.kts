plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

gradlePlugin {
    plugins {
        register("build-config-plugin") {
            id = "com.news.compose.build.config"
            implementationClass = "com.news.compose.convention.plugins.BuildConfigPlugin"
        }
        register("screen-module-plugin") {
            id = "com.news.compose.module.screen"
            implementationClass = "com.news.compose.convention.plugins.ScreenModulePlugin"
        }
        register("dagger-module-plugin") {
            id = "com.news.compose.module.dagger"
            implementationClass = "com.news.compose.convention.plugins.DaggerModulePlugin"
        }
        register("compose-module-plugin") {
            id = "com.news.compose.module.compose"
            implementationClass = "com.news.compose.convention.plugins.ComposeModulePlugin"
        }
    }
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.google.ksp)
    compileOnly(gradleApi())
}