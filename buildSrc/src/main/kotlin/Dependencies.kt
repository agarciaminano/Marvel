import Dependencies.hilt
import Dependencies.kotlin
import Dependencies.testing
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object Dependencies {

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"

    val modules = mapOf(
        "app" to ":app",
        "feature-common" to ":feature-common"
    )

    val androidx = mapOf(
        "core" to "androidx.core:core-ktx:${Versions.androidx["core"]}",
        "appcompat" to "androidx.appcompat:appcompat:${Versions.androidx["core"]}",
        "constraintLayout" to "androidx.constraintlayout:constraintlayout:2.0.1",
        "activity" to "androidx.activity:activity-ktx:${Versions.androidx["activity"]}"
    )

    val livedata = mapOf(
        "core" to "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.livedata}",
        "ktx" to "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata}"
    )

    val navigation = mapOf(
        "fragment" to "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}",
        "ui" to "androidx.navigation:navigation-ui-ktx:${Versions.navigation}",
        "testing" to "androidx.navigation:navigation-testing:${Versions.navigation}",
        "safe-args" to "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    )


    val testing = mapOf(
        "junit" to "junit:junit:${Versions.testing["junit"]}",
        "junitAndroid" to "androidx.test.ext:junit:${Versions.testing["junitAndroid"]}",
        "espresso" to "androidx.test.espresso:espresso-core:${Versions.testing["espresso"]}"
    )

    val hilt = mapOf(
        "hilt-implementation" to "com.google.dagger:hilt-android:${Versions.hilt["hilt"]}",
        "hilt-kapt" to "com.google.dagger:hilt-android-compiler:${Versions.hilt["hilt"]}",
        "viewmodel-implementation" to "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt["viewmodel"]}",
        "viewmodel-kapt" to "androidx.hilt:hilt-compiler:${Versions.hilt["viewmodel"]}"
    )


    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"


}

fun DependencyHandlerScope.addTestDependencies() {
    add("testImplementation", "${testing["junit"]}")
    add("androidTestImplementation", "${testing["espresso"]}")
    add("androidTestImplementation", "${testing["junit"]}")
}

fun DependencyHandlerScope.addDiDependencies() {
    add("implementation", "${hilt["hilt-implementation"]}")
    add("kapt", "${hilt["hilt-kapt"]}")
    add("implementation", "${hilt["viewmodel-implementation"]}")
    add("kapt", "${hilt["viewmodel-kapt"]}")

}

fun DependencyHandlerScope.addAndroidBaseDepencencies() {
    add("implementation", kotlin)
    add("implementation", Dependencies.androidx["appcompat"].asAny())
    add("implementation", Dependencies.androidx["core"].asAny())
    add("implementation", Dependencies.androidx["constraintLayout"].asAny())
    add("implementation", Dependencies.navigation["fragment"].asAny())
    add("implementation", Dependencies.navigation["ui"].asAny())
    add("implementation", Dependencies.livedata["core"].asAny())
    add("implementation", Dependencies.livedata["ktx"].asAny())
    add("androidTestImplementation", Dependencies.navigation["testing"].asAny())
}

fun DependencyHandlerScope.addBaseModuleDepencencies() {
    add(
        "implementation",
        project(":feature-common")
    )
}

fun DependencyHandlerScope.addFeatureModuleDepencencies() {
    add(
        "implementation",
        project(":feature-home")
    )
    add(
        "implementation",
        project(":feature-common")
    )
}


private fun String?.asAny(): Any = this as Any
