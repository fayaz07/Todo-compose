plugins {
  id(Plugins.androidLibrary)
  id(Plugins.kotlinAndroid)
}

android {
  namespace = Modules.namespaces.dashboard
  compileSdk = Config.compileSdk

  defaultConfig {
    minSdk = Config.minSdk
    targetSdk = Config.targetSdk

    testInstrumentationRunner = Config.testInstrumentationRunner
    consumerProguardFiles(Config.proguard.consumerRules)
    vectorDrawables {
      useSupportLibrary = true
    }
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
  packagingOptions {
    resources {
      excludes += Config.packagingOptions.excludedResources
    }
  }
}

dependencies {
  implementation(project(Modules.core))
  implementation(project(Modules.designKit))
  androidTestImplementation(project(Modules.utils.androidTest))

  implementation(Dependencies.AndroidX.core)
  implementation(Dependencies.AndroidX.lifecycleRuntime)

  implementation(Dependencies.Compose.ui)
  implementation(Dependencies.Compose.material)
  implementation(Dependencies.Compose.toolingPreview)
  implementation(Dependencies.Compose.activity)
  implementation(Dependencies.Compose.navigation)

  testImplementation(Dependencies.Test.junit)
  androidTestImplementation(Dependencies.Test.androidxJUnit)
  androidTestImplementation(Dependencies.Test.expressoCore)
  androidTestImplementation(Dependencies.Test.composeJunit)
  androidTestImplementation(Dependencies.Test.composeManifest)
  debugImplementation(Dependencies.Compose.uiTooling)
}
