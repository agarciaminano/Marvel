object Dependencies {

     const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

     val androidx = mapOf(
          "core" to "androidx.core:core-ktx:${Versions.androidx["core"]}",
          "appcompat" to "androidx.appcompat:appcompat:${Versions.androidx["core"]}"

     )



     val testing = mapOf(
          "junit" to "junit:junit:${Versions.testing["junit"]}",
          "junitAndroid" to "androidx.test.ext:junit:${Versions.testing["junitAndroid"]}",
          "espresso" to "androidx.test.espresso:espresso-core:${Versions.testing["espresso"]}"
     )

}
