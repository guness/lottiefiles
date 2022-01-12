buildscript {

    extra["versions"] = mapOf(
        "minSdk" to 21,
        "targetSdk" to 31,
        "compileSdk" to 31
    )
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Android.tools.build.gradlePlugin)
        classpath(Google.playServicesGradlePlugin)
        classpath(Firebase.crashlyticsGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
        classpath("org.jetbrains.kotlin:kotlin-serialization:_")
        classpath(Google.dagger.hilt.android.gradlePlugin)
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}


tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}
