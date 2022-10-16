object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val dagger = "com.google.dagger.hilt.android"
    const val kapt = "kapt"
}

object PluginVersions {
    const val androidApplication = "7.3.0"
    const val kotlinAndroid = "1.7.20"

    const val detekt = "1.22.0-RC1"

    const val dagger = Versions.DAGGER
}

object BuildPlugins {
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-formatting:${PluginVersions.detekt}"
}
