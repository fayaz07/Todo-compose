import org.gradle.api.JavaVersion

object Config {
  const val compileSdk = 33
  const val targetSdk = compileSdk
  const val minSdk = 21
  const val versionCode = 1
  const val versionName = "0.1"

  const val jvmTarget = "1.8"
  const val ktCompilerExtVersion = "1.3.2"

  val javaSourceCompatability = JavaVersion.VERSION_1_8
  val javaTargetCompatability = JavaVersion.VERSION_1_8

//  const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  const val testInstrumentationRunner = "com.fayaz.todo_jc.core.TodoTestRunner"

  object detekt {
    const val configFilePath = "detekt.yml"
  }

  object proguard {
    const val defaultProguardFile = "proguard-android-optimize.txt"
    const val proguardRules = "proguard-rules.pro"
    const val consumerRules = "consumer-rules.pro"
  }

  object packagingOptions {
    const val excludedResources = "/META-INF/{AL2.0,LGPL2.1}"
  }
}
