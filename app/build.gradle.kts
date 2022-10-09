plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.fayaz.todo_jc"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Modules.designKit))

    implementation(Dependencies.AndroidX.splashScreen)
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.lifecycleRuntime)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.activity)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.androidxJUnit)
    androidTestImplementation(Dependencies.Test.expressoCore)
    androidTestImplementation(Dependencies.Test.composeJunit)
    debugImplementation(Dependencies.Compose.uiTooling)
}
