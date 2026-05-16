plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(projects.app.shared)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.lifecycle.runtime)
}

@Suppress("UnstableApiUsage")
android {
    buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
    compileSdk {
        version = release(BuildConfig.COMPILE_SDK) {
            minorApiLevel = BuildConfig.COMPILE_SDK_MINOR
        }
    }
    defaultConfig {
        applicationId = BuildConfig.APPLICATION_ID
        minSdk = BuildConfig.MIN_SDK
        targetSdk = BuildConfig.TARGET_SDK
        versionName = BuildConfig.APPLICATION_VERSION_NAME
        versionCode = 1
    }
    namespace = BuildConfig.APPLICATION_ID
    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}

base {
    archivesName.set(
        "${BuildConfig.APPLICATION_NAME}-v${BuildConfig.APPLICATION_VERSION_NAME}",
    )
}