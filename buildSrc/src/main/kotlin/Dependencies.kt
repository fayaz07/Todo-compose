import Versions.COMPOSE
import Versions.SPLASH_SCREEN
import Versions.ANDROIDX_CORE
import Versions.LIFECYCLE_RUNTIME
import Versions.ACTIVITY_COMPOSE
import Versions.JUNIT
import Versions.ANDROIDX_JUNIT
import Versions.APP_COMPAT
import Versions.EXPRESSO_CORE
import Versions.NAVIGATION
import Versions.ACCOMPANIST
import Versions.DAGGER
import Versions.LEAK_CANARY
import Versions.TIMBER

object Dependencies {
  object JakeWharton {
    const val timber = "com.jakewharton.timber:timber:$TIMBER"
  }

  object Square {
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$LEAK_CANARY"
  }

  object Google {
    object Accompanist {
      val pager by lazy { "com.google.accompanist:accompanist-pager:$ACCOMPANIST" }
      val pagerIndicators by lazy { "com.google.accompanist:accompanist-pager-indicators:$ACCOMPANIST" }
    }
  }

  object AndroidX {
    val splashScreen by lazy { "androidx.core:core-splashscreen:$SPLASH_SCREEN" }
    val core by lazy { "androidx.core:core-ktx:$ANDROIDX_CORE" }
    val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_RUNTIME" }
    val appCompat by lazy { "androidx.appcompat:appcompat:$APP_COMPAT" }
  }

  object Compose {
    val ui by lazy { "androidx.compose.ui:ui:$COMPOSE" }
    val material by lazy { "androidx.compose.material:material:$COMPOSE" }
    val toolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:$COMPOSE" }
    val activity by lazy { "androidx.activity:activity-compose:$ACTIVITY_COMPOSE" }
    val uiTooling by lazy { "androidx.compose.ui:ui-tooling:$COMPOSE" }
    val navigation by lazy { "androidx.navigation:navigation-compose:$NAVIGATION" }
  }

  object Test {
    val junit by lazy { "junit:junit:$JUNIT" }
    val androidxJUnit by lazy { "androidx.test.ext:junit:$ANDROIDX_JUNIT" }
    val expressoCore by lazy { "androidx.test.espresso:espresso-core:$EXPRESSO_CORE" }
    val composeJunit by lazy { "androidx.compose.ui:ui-test-junit4:$COMPOSE" }
    val composeManifest by lazy { "androidx.compose.ui:ui-test-manifest:$COMPOSE" }
  }

  object DI {
    val dagger by lazy { "com.google.dagger:hilt-android:$DAGGER" }
    val daggerKapt by lazy { "com.google.dagger:hilt-compiler:$DAGGER" }
    val daggerKapt2 by lazy { "com.google.dagger:hilt-android-compiler:$DAGGER" }
  }
}
