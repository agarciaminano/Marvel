plugins {
    id(Plugins.ANDROID_APP)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.ANDROID_HILT)
    id(Plugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion (Versions.compileSdk)
    buildToolsVersion (Versions.buildTools)

    defaultConfig {
        applicationId = "com.example.marvel"
        minSdkVersion (Versions.minSdk)
        targetSdkVersion (Versions.targetSdk)
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation (fileTree("libs") { include(listOf("*.jar")) } )
    addAndroidBaseDepencencies()
    addTestDependencies()
    addDiDependencies()

}