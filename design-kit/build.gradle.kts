plugins {
  id(Plugins.androidLibrary)
  id(Plugins.kotlinAndroid)
}

android {
  namespace = Config.designKit
  compileSdk = Config.compileSdk

  defaultConfig {
    minSdk = Config.minSdk
    targetSdk = Config.targetSdk

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
  compileOptions {
    sourceCompatibility = Config.javaSourceCompatability
    targetCompatibility = Config.javaTargetCompatability
  }
  kotlinOptions {
    jvmTarget = Config.jvmTarget
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = Config.ktCompilerExtVersion
  }
}

dependencies {

  implementation(Dependencies.AndroidX.core)

  implementation(Dependencies.Compose.toolingPreview)
  implementation(Dependencies.Compose.material)
  implementation(Dependencies.Compose.ui)

  testImplementation(Dependencies.Test.junit)
  androidTestImplementation(Dependencies.Test.androidxJUnit)
  androidTestImplementation(Dependencies.Test.expressoCore)
  androidTestImplementation(Dependencies.Test.composeJunit)
}
