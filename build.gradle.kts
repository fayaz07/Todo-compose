// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.androidApplication) version PluginVersions.androidApplication apply false
    id(Plugins.androidLibrary) version PluginVersions.androidApplication apply false
    id(Plugins.kotlinAndroid) version PluginVersions.kotlinAndroid apply false
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}