import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.androidApplication) version PluginVersions.androidApplication apply false
    id(Plugins.androidLibrary) version PluginVersions.androidApplication apply false
    id(Plugins.kotlinAndroid) version PluginVersions.kotlinAndroid apply false
    id(Plugins.detekt) version PluginVersions.detekt apply true
}

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(BuildPlugins.detekt)
    }
}

detekt {
    toolVersion = PluginVersions.detekt
    config = files("$rootDir/detekt.yml")
}

tasks.withType<Detekt> {
    include("**/*.kt")
    include("**/*.kts")
    exclude(".*/resources/.*")
    exclude(".*/build/.*")
    exclude("/versions.gradle.kts")
    exclude("buildSrc/settings.gradle.kts")
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}
