import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id(Plugins.androidApplication) version PluginVersions.agp apply false
  id(Plugins.androidLibrary) version PluginVersions.agp apply false
  id(Plugins.kotlinAndroid) version PluginVersions.kotlin apply false
  id(Plugins.detekt) version PluginVersions.detekt apply true
  id(Plugins.dagger) version PluginVersions.dagger apply false
  id(Plugins.kotlinJvm) version PluginVersions.kotlin apply false
}

tasks.register<Delete>(Tasks.CLEANUP).configure {
  delete(rootProject.buildDir)
}

detekt {
  toolVersion = PluginVersions.detekt
  config = files("$rootDir/${Config.detekt.configFilePath}")
}

tasks.withType<Detekt> {
  include("**/*.kt")
  include("**/*.kts")
  exclude(".*/resources/.*")
  exclude(".*/build/.*")
  exclude("/versions.gradle.kts")
  exclude("buildSrc/settings.gradle.kts")
}

tasks.withType<Detekt>().configureEach {
  jvmTarget = Config.jvmTarget
}

tasks.withType<DetektCreateBaselineTask>().configureEach {
  jvmTarget = Config.jvmTarget
}

allprojects {
  apply(plugin = Plugins.detekt)

  detekt {
    config = files("$rootDir/${Config.detekt.configFilePath}")

    allRules = true
    buildUponDefaultConfig = true
  }
}
