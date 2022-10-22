plugins {
  id(Plugins.androidApplication)
  id(Plugins.kotlinAndroid)
  id(Plugins.dagger)
  kotlin(Plugins.kapt)
}

android {
  compileSdk = Config.compileSdk

  defaultConfig {
    applicationId = Modules.namespaces.appId
    minSdk = Config.minSdk
    targetSdk = Config.targetSdk
    versionCode = Config.versionCode
    versionName = Config.versionName

    testInstrumentationRunner = Config.testInstrumentationRunner
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    getByName(BuildTypes.RELEASE) {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile(Config.proguard.defaultProguardFile),
        Config.proguard.proguardRules
      )
    }
  }
  compileOptions {
    sourceCompatibility = Config.javaSourceCompatability
    targetCompatibility = Config.javaSourceCompatability
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
  implementation(project(Modules.designKit))
  implementation(project(Modules.core))
  implementation(project(Modules.feature.onboarding))
  implementation(project(Modules.feature.dashboard))

  implementation(Dependencies.DI.dagger)
  kapt(Dependencies.DI.daggerKapt)
  androidTestImplementation(Dependencies.DI.hiltTest)
  kaptAndroidTest(Dependencies.DI.hiltKaptTest)

  implementation(Dependencies.DI.hiltTest)
  kapt(Dependencies.DI.hiltKaptTest)

  implementation(Dependencies.Test.runner)

  implementation(Dependencies.AndroidX.splashScreen)
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
  debugImplementation(Dependencies.Compose.uiTooling)
}

kapt {
  correctErrorTypes = true
}

hilt {
  enableAggregatingTask = true
}
