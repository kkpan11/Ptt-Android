plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    kotlin("android")
}

fun String.runCommand(workingDir: File = file("./")): String {
    val parts = this.split("\\s".toRegex())
    val proc = ProcessBuilder(*parts.toTypedArray())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    proc.waitFor(1, TimeUnit.MINUTES)
    return proc.inputStream.bufferedReader().readText().trim()
}

fun gitSha(): String {
    return "git rev-parse --short HEAD".runCommand()
}

android {
    compileSdk = GlobalConfig.ANDROID_BUILD_SDK_VERSION
    buildToolsVersion = GlobalConfig.ANDROID_BUILD_TOOLS_VERSION

    namespace = "cc.ptt.android"
    buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = GlobalConfig.applicationId
        minSdk = GlobalConfig.ANDROID_BUILD_MIN_SDK_VERSION
        targetSdk = GlobalConfig.ANDROID_BUILD_TARGET_SDK_VERSION
        versionCode = GlobalConfig.versionCode
        versionName = "${GlobalConfig.versionName}, ${gitSha()}"
        GlobalConfig.testInstrumentationRunner
        multiDexEnabled = true
        ndk {
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
            abiFilters.add("x86")
            abiFilters.add("x86_64")
        }
    }

    signingConfigs {
        create("ptt_dev") {
            storeFile = file("debug.jks")
            storePassword = "PttNeverDie"
            keyAlias = "Ptt"
            keyPassword = "PttNeverDie"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("ptt_dev")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isShrinkResources = true
            isZipAlignEnabled = true
            multiDexEnabled = true
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("ptt_dev")
            isDefault = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isShrinkResources = false
            isZipAlignEnabled = false
            multiDexEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = GlobalConfig.JDKVersion
        targetCompatibility = GlobalConfig.JDKVersion
    }

    buildFeatures {
        viewBinding = true
    }

    flavorDimensions += "api_environment"
    productFlavors {
        create("production") {
            dimension = "api_environment"
        }

        create("staging") {
            isDefault = true
            dimension = "api_environment"
        }
    }
}

dependencies {
    api(project(":common"))
    api(project(":data"))
    api(project(":domain"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // AndroidX
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.browser)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.legacy)
    implementation(Dependencies.AndroidX.Lifecycle.compiler)
    implementation(Dependencies.AndroidX.Lifecycle.viewModelKTX)
    implementation(Dependencies.AndroidX.Lifecycle.runtime)
    implementation(Dependencies.AndroidX.localBroadcastManager)
    implementation(Dependencies.AndroidX.multidex)
    implementation(Dependencies.AndroidX.swipeRefreshLayout)
    implementation(Dependencies.AndroidX.Navigation.fragment)
    implementation(Dependencies.AndroidX.Navigation.ui)

    // Coil
    implementation(Dependencies.coil)

    // Google
    implementation(Dependencies.Google.material)
    implementation(Dependencies.Google.gson)

    // Jsoup
    implementation(Dependencies.jsoup)

    // Kotlin
    //implementation(Dependencies.Kotlin.Stdlib.jdk8)
    implementation(Dependencies.Kotlin.Coroutines.core)
    implementation(Dependencies.Kotlin.Coroutines.android)
    // Square
    implementation(Dependencies.Square.okhttp)
    implementation(Dependencies.Square.log)
    implementation(Dependencies.Square.okio)
    implementation(Dependencies.Square.Retrofit.core)
    implementation(Dependencies.Square.Retrofit.gsonConverter)
    
    // Koin Core features
    implementation(Dependencies.Koin.Core.core)
    // Koin main features for Android
    implementation(Dependencies.Koin.Android.android)
    implementation(Dependencies.AndroidX.coreKtx)

    // Test
    testImplementation(Dependencies.Google.truth)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.MockK.core)
    testImplementation(Dependencies.AndroidX.arch)
    testImplementation(Dependencies.Kotlin.Coroutines.test)

    // Koin Test features
    testImplementation(Dependencies.Koin.Core.test)
    // Koin for JUnit 4
    testImplementation(Dependencies.Koin.Core.junit4)
    testImplementation(Dependencies.AndroidX.Test.Core.core)

    androidTestImplementation(Dependencies.AndroidX.Test.Ext.junit)
    androidTestImplementation(Dependencies.AndroidX.Test.Ext.espresso)
    androidTestImplementation(Dependencies.Google.truth)
    androidTestImplementation(Dependencies.MockK.android)
    androidTestImplementation(Dependencies.AndroidX.arch)
    androidTestImplementation(Dependencies.Kotlin.Coroutines.test)

    debugImplementation(Dependencies.Square.leakcanary)
}

