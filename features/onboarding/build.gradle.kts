plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = Config.featureOnboarding
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.Google.Accompanist.pager)
    implementation(Dependencies.Google.Accompanist.pagerIndicators)
    implementation(project(Modules.designKit))
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