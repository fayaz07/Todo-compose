plugins {
  id(Plugins.androidLibrary)
  id(Plugins.kotlinAndroid)
  id(Plugins.dagger)
  kotlin(Plugins.kapt)
}

android {
  namespace = Modules.namespaces.featureOnboarding
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
  implementation(project(Modules.domain))
  implementation(project(Modules.data.prefs))
  implementation(project(Modules.designKit))
  androidTestImplementation(project(Modules.utils.androidTest))

  implementation(Dependencies.DI.dagger)
  kapt(Dependencies.DI.daggerKapt)

  androidTestImplementation(Dependencies.DI.hiltTest)
  kaptAndroidTest(Dependencies.DI.hiltKaptTest)

  implementation(Dependencies.Google.Accompanist.pager)
  implementation(Dependencies.Google.Accompanist.pagerIndicators)
  implementation(Dependencies.Google.Accompanist.permissions)

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

kapt {
  correctErrorTypes = true
}

hilt {
  enableAggregatingTask = true
}
