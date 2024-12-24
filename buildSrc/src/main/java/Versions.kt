import org.gradle.api.JavaVersion

object Versions {
    const val majorVersion = 0
    const val minorVersion = 18
    const val patchVersion = 4

    const val androidGradle = "8.7.3"
    const val kotlin = "1.9.20"
    const val androidXAppCompat = "1.7.0"
    const val androidXArch = "2.2.0"
    const val androidXBrowser = "1.8.0"
    const val androidXConstraintLayout = "2.2.0"
    const val androidXCore = "1.15.0"
    const val androidXLegacy = "1.0.0"
    const val androidXLifecycle = "2.8.7"
    const val androidXLocalBroadcastManager = "1.1.0"
    const val androidXMultidex = "2.0.1"
    const val androidXNavigation = "2.8.5"
    const val androidXSwipeRefreshLayout = "1.1.0"
    const val androidXTestCore = "1.6.1"
    const val androidXTestEspresso = "3.6.1"
    const val androidXTestJunit = "1.2.1"
    const val jsoup = "1.18.3"
    const val junit = "4.13.2"
    const val material = "1.12.0"
    const val mockK = "1.13.13"
    const val okhttp = "4.12.0"
    const val okio = "3.9.1"
    const val retrofit = "2.11.0"
    const val truth = "1.4.4"
    const val leakcanary = "2.14"
    const val gson = "2.11.0"
    const val coroutines = "1.9.0"
    const val coil = "2.7.0"
    const val hilt = "2.53.1"
    const val koinCore = "4.0.0"
    const val koinAndroid = "4.0.0"
    const val spotless = "6.12.0"
}

object GlobalConfig {
    const val ANDROID_BUILD_SDK_VERSION = 35
    const val ANDROID_BUILD_MIN_SDK_VERSION = 23
    const val ANDROID_BUILD_TARGET_SDK_VERSION = 35
    const val ANDROID_BUILD_TOOLS_VERSION = "35.0.0"

    const val applicationId: String = "cc.ptt.android"
    const val versionCode: Int = Versions.majorVersion * 1000000 + Versions.minorVersion * 10000 + Versions.patchVersion * 100
    const val versionName = "${Versions.majorVersion}.${Versions.minorVersion}.${Versions.patchVersion}"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    val JDKVersion = JavaVersion.VERSION_21

    const val BUILD_CONFIG_KEY_FOR_API_HOST = "API_HOST"
    const val BUILD_CONFIG_KEY_FOR_TEST_ACCOUNT = "TEST_ACCOUNT"
    const val BUILD_CONFIG_KEY_FOR_TEST_PASSWORD = "TEST_PASSWORD"
}