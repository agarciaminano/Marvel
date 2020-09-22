import Dependencies.hilt
import Dependencies.kotlin
import Dependencies.testing
import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependencies {

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val material = "com.google.android.material:material:${Versions.material}"

    val androidx = mapOf(
        "core" to "androidx.core:core-ktx:${Versions.androidx["core"]}",
        "appcompat" to "androidx.appcompat:appcompat:${Versions.androidx["core"]}",
        "constraintLayout" to "androidx.constraintlayout:${Versions.androidx["constraintlayout"]}",
        "activity" to "androidx.activity:activity-ktx:${Versions.androidx["activity"]}"
    )

    val navigation = mapOf(
        "fragment" to "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}",
        "ui" to "androidx.navigation:navigation-ui-ktx:${Versions.navigation}",
        "testing" to "androidx.navigation:navigation-testing:${Versions.navigation}",
        "safe-args" to  "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
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
    add("implementation", "${Dependencies.androidx["core"]}")
    add("implementation", "${Dependencies.androidx["appcompat"]}")
    add("implementation", "${Dependencies.navigation["fragment"]}")
    add("implementation", "${Dependencies.navigation["ui"]}")
    add("androidTestImplementation", "${Dependencies.navigation["testing"]}")
}

