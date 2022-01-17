import de.fayard.refreshVersions.core.versionFor
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {

    val versions: Map<String, Int> by rootProject.extra

    compileSdk = versions["compileSdk"]

    defaultConfig {
        minSdk = versions["minSdk"]
        targetSdk = versions["targetSdk"]
        versionCode = 1 // managed by Bitrise
        versionName = "1.0.0" // managed by Bitrise

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.incremental", "true")
                arg("room.expandProjection", "true")
            }
        }

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        all {
            buildConfigField("String", "URL_TOS", "\"https://riseaffirmation.com/termsofservice\"")
            buildConfigField("String", "URL_PRIVACY", "\"https://riseaffirmation.com/privacy\"")
        }
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(
                listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            )
        }
        named("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler)
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xopt-in=" +
                    "kotlin.RequiresOptIn," +
                    "kotlinx.coroutines.ObsoleteCoroutinesApi," +
                    "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                    "androidx.compose.material.ExperimentalMaterialApi," +
                    "androidx.compose.foundation.ExperimentalFoundationApi," +
                    "kotlinx.serialization.ExperimentalSerializationApi"
        )
    }
}

dependencies {
    // Android Jetpack Compose
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.compose.foundation)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.material.icons.core)
    implementation(AndroidX.compose.material.icons.extended)
    implementation(AndroidX.compose.runtime)
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.preference.ktx)
    implementation(Google.android.material)

    // Navigation
    implementation(AndroidX.navigation.compose)
    implementation(AndroidX.hilt.navigationCompose)

    // Lifecycle
    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.viewModelCompose)

    // Room
    implementation(AndroidX.room.runtime)
    kapt(AndroidX.room.compiler)
    implementation(AndroidX.room.ktx)

    // Dependency injection
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    // Kotlin coroutines
    implementation(KotlinX.coroutines.android)
    testImplementation(KotlinX.coroutines.test)

    // Json lib
    implementation(KotlinX.serialization.json)
    implementation(JakeWharton.retrofit2.converter.kotlinxSerialization)

    // Tools
    implementation(JakeWharton.timber)

    // Internet
    implementation(COIL.compose)
    implementation(Square.retrofit2)
    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)
    implementation("com.airbnb.android:lottie-compose:_")

    // Utilities
    coreLibraryDesugaring(Android.tools.desugarJdkLibs)


    // Testing
    testImplementation(Testing.junit4)
    testImplementation(Testing.robolectric)
    testImplementation(Square.okHttp3.mockWebServer)
    testImplementation(AndroidX.test.core)
    testImplementation(AndroidX.test.ext.junit)
    testImplementation(AndroidX.test.monitor)
    testImplementation(AndroidX.test.espresso.core)
    testImplementation(AndroidX.compose.ui.testJunit4)
}

kapt {
    correctErrorTypes = true
}
