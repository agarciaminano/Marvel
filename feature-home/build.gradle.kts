
plugins {
    id("com.android.application")
    id("kotlin-android")
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
    implementation (Dependencies.kotlin)
    implementation ("${Dependencies.androidx["core"]}")
    implementation ("${Dependencies.androidx["appcompat"]}")
    testImplementation ("${Dependencies.testing["junit"]}")
    androidTestImplementation ("${Dependencies.testing["espresso"]}")
    androidTestImplementation ("${Dependencies.testing["junit"]}")

}