plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
}

android {
    namespace = "com.example.miuixdemo"

    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.miuixdemo"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0.0"
    }

    val keystorePath = System.getenv("KEYSTORE_PATH")
    val keystorePwd = System.getenv("KEYSTORE_PASS")
    val keyAlias = System.getenv("KEY_ALIAS")
    val keyPwd = System.getenv("KEY_PASSWORD")
    val useReleaseSigning = !keystorePath.isNullOrBlank()

    signingConfigs {
        create("release") {
            if (useReleaseSigning) {
                storeFile = file(keystorePath)
                storePassword = keystorePwd
                keyAlias = keyAlias
                keyPassword = keyPwd
            }
            enableV3Signing = true
            enableV4Signing = true
        }
    }

    buildTypes {
        release {
            vcsInfo.include = false
            signingConfig = signingConfigs.getByName(if (useReleaseSigning) "release" else "debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        jniLibs {
            excludes += "lib/*/libandroidx.graphics.path.so"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.ui)

    implementation(libs.miuix.ui)
    implementation(libs.miuix.preference)
    implementation(libs.miuix.icons)

    testImplementation(libs.junit)
}
