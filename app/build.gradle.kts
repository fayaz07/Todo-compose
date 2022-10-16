plugins {
  id(Plugins.androidApplication)
  id(Plugins.kotlinAndroid)
}

android {
  compileSdk = Config.compileSdk

  defaultConfig {
    applicationId = Config.appId
    minSdk = Config.minSdk
    targetSdk = Config.targetSdk
    versionCode = Config.versionCode
    versionName = Config.versionName

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
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
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation(project(Modules.designKit))
  implementation(project(Modules.feature.onboarding))

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
