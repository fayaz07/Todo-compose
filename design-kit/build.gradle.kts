plugins {
  id(Plugins.androidLibrary)
  id(Plugins.kotlinAndroid)
}

android {
  namespace = Modules.namespaces.designKit
  compileSdk = Config.compileSdk

  defaultConfig {
    minSdk = Config.minSdk
    targetSdk = Config.targetSdk

    testInstrumentationRunner = Config.testInstrumentationRunner
    consumerProguardFiles(Config.proguard.consumerRules)
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile(Config.proguard.defaultProguardFile),
        Config.proguard.proguardRules
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

  implementation(project(Modules.core))
  implementation(project(Modules.utils.core))

  implementation(Dependencies.Compose.toolingPreview)
  implementation(Dependencies.Compose.material)
  implementation(Dependencies.Compose.ui)
  debugImplementation(Dependencies.Compose.uiTooling)

  testImplementation(Dependencies.Test.junit)
  androidTestImplementation(Dependencies.Test.androidxJUnit)
  androidTestImplementation(Dependencies.Test.expressoCore)
  androidTestImplementation(Dependencies.Test.composeJunit)
}
