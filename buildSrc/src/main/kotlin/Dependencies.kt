import Versions.COMPOSE
import Versions.SPLASH_SCREEN
import Versions.ANDROIDX_CORE
import Versions.LIFECYCLE_RUNTIME
import Versions.ACTIVITY_COMPOSE
import Versions.JUNIT
import Versions.ANDROIDX_JUNIT
import Versions.EXPRESSO_CORE

object Dependencies {
    object AndroidX {
        val splashScreen by lazy { "androidx.core:core-splashscreen:$SPLASH_SCREEN" }
        val core by lazy { "androidx.core:core-ktx:$ANDROIDX_CORE" }
        val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_RUNTIME" }
    }

    object Compose {
        val ui by lazy { "androidx.compose.ui:ui:$COMPOSE" }
        val material by lazy { "androidx.compose.material:material:$COMPOSE" }
        val toolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:$COMPOSE" }
        val activity by lazy { "androidx.activity:activity-compose:$ACTIVITY_COMPOSE" }
        val uiTooling by lazy { "androidx.compose.ui:ui-tooling:$COMPOSE" }
    }

    object Test {
        val junit by lazy { "junit:junit:$JUNIT" }
        val androidxJUnit by lazy { "androidx.test.ext:junit:$ANDROIDX_JUNIT" }
        val expressoCore by lazy { "androidx.test.espresso:espresso-core:$EXPRESSO_CORE" }
        val composeJunit by lazy { "androidx.compose.ui:ui-test-junit4:$COMPOSE" }
    }
}