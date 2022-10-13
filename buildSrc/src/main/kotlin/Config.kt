import org.gradle.api.JavaVersion

object Config {
    // modules and appId
    const val appId = "com.fayaz.todo_jc"
    const val designKit = "com.fayaz.todo_jc.design_kit"
    const val featureOnboarding = "com.fayaz.todo_jc.features.onboarding"

    const val compileSdk = 33
    const val targetSdk = compileSdk
    const val minSdk = 21
    const val versionCode = 1
    const val versionName = "0.1"


    const val jvmTarget = "1.8"
    const val ktCompilerExtVersion = "1.3.2"

    val javaSourceCompatability = JavaVersion.VERSION_1_8
    val javaTargetCompatability = JavaVersion.VERSION_1_8

}
