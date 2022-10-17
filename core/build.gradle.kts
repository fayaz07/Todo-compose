plugins {
  id(Plugins.androidLibrary)
  id(Plugins.kotlinAndroid)
  id(Plugins.dagger)
  kotlin(Plugins.kapt)
}

android {
  namespace = Modules.namespaces.core
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
  packagingOptions {
    resources {
      excludes += Config.packagingOptions.excludedResources
    }
  }
}

dependencies {
  implementation(Dependencies.AndroidX.core)
  implementation(Dependencies.AndroidX.lifecycleRuntime)
  implementation(Dependencies.JakeWharton.timber)

  implementation(Dependencies.AndroidX.lifecycleRuntime)
  implementation(Dependencies.Compose.ui)

  implementation(Dependencies.DI.dagger)
  kapt(Dependencies.DI.daggerKapt)

  testImplementation(Dependencies.Test.junit)
  androidTestImplementation(Dependencies.Test.androidxJUnit)
}

kapt {
  correctErrorTypes = true
}

hilt {
  enableAggregatingTask = true
}
