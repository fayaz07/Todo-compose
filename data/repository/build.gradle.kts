plugins {
  id(Plugins.androidLibrary)
  id(Plugins.kotlinAndroid)
  id(Plugins.dagger)
  kotlin(Plugins.kapt)
}

android {
  namespace = Modules.namespaces.repository
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
}

dependencies {
  implementation(project(Modules.domain))
  implementation(project(Modules.data.prefs))

  implementation(Dependencies.DI.dagger)
  kapt(Dependencies.DI.daggerKapt)
}