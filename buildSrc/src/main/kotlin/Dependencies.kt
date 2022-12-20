import Versions.ACCOMPANIST
import Versions.ACTIVITY_COMPOSE
import Versions.ANDROIDX_CORE
import Versions.ANDROIDX_JUNIT
import Versions.APP_COMPAT
import Versions.COMPOSE
import Versions.COMPOSE_MATERIAL
import Versions.DAGGER
import Versions.DATASTORE
import Versions.EXPRESSO_CORE
import Versions.FLUENT
import Versions.HILT_NAVIGATION_COMPOSE
import Versions.JUNIT
import Versions.LEAK_CANARY
import Versions.LIFECYCLE_RUNTIME
import Versions.NAVIGATION
import Versions.SPLASH_SCREEN
import Versions.TEST_RUNNER
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
      private const val accompanistBase = "com.google.accompanist:accompanist"
      val pager by lazy { "${accompanistBase}-pager:$ACCOMPANIST" }
      val pagerIndicators by lazy { "${accompanistBase}-pager-indicators:$ACCOMPANIST" }
      val flowLayouts by lazy { "${accompanistBase}-flowlayout:$ACCOMPANIST" }
    }
  }

  object AndroidX {
    val splashScreen by lazy { "androidx.core:core-splashscreen:$SPLASH_SCREEN" }
    val core by lazy { "androidx.core:core-ktx:$ANDROIDX_CORE" }
    val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_RUNTIME" }
    val appCompat by lazy { "androidx.appcompat:appcompat:$APP_COMPAT" }
    val datastore by lazy { "androidx.datastore:datastore-preferences:$DATASTORE" }
    val datastoreCore by lazy { "androidx.datastore:datastore-preferences-core:$DATASTORE" }
  }

  object Compose {
    val ui by lazy { "androidx.compose.ui:ui:$COMPOSE" }
    val material by lazy { "androidx.compose.material:material:$COMPOSE_MATERIAL" }
    val toolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:$COMPOSE" }
    val activity by lazy { "androidx.activity:activity-compose:$ACTIVITY_COMPOSE" }
    val uiTooling by lazy { "androidx.compose.ui:ui-tooling:$COMPOSE" }
    val navigation by lazy { "androidx.navigation:navigation-compose:$NAVIGATION" }
  }

  object Test {
    val fluent by lazy { "org.amshove.kluent:kluent:$FLUENT" }
    val junit by lazy { "junit:junit:$JUNIT" }
    val androidxJUnit by lazy { "androidx.test.ext:junit:$ANDROIDX_JUNIT" }
    val expressoCore by lazy { "androidx.test.espresso:espresso-core:$EXPRESSO_CORE" }
    val composeJunit by lazy { "androidx.compose.ui:ui-test-junit4:$COMPOSE" }
    val composeManifest by lazy { "androidx.compose.ui:ui-test-manifest:$COMPOSE" }
    val runner by lazy { "androidx.test:runner:$TEST_RUNNER" }
  }

  object DI {
    val navigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:$HILT_NAVIGATION_COMPOSE" }
    val dagger by lazy { "com.google.dagger:hilt-android:$DAGGER" }
    val daggerKapt by lazy { "com.google.dagger:hilt-compiler:$DAGGER" }
    val hiltTest by lazy { "com.google.dagger:hilt-android-testing:$DAGGER" }
    val hiltKaptTest by lazy { "com.google.dagger:hilt-android-compiler:$DAGGER" }
  }
}
