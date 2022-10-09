plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "com.fayaz.todo_jc.design_kit"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

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
}

dependencies {

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.androidxJUnit)
    androidTestImplementation(Dependencies.Test.expressoCore)
    androidTestImplementation(Dependencies.Test.composeJunit)
}