object Modules {
  const val designKit = ":design-kit"
  const val core = ":core"

  object utils {
    private const val utils = ":utils"
    const val androidTest = utils + ":android-test"
  }
  object feature {
    private const val feature = ":features"
    const val onboarding = feature + ":onboarding"
    const val dashboard = feature + ":dashboard"
  }

  object namespaces {
    const val appId = "com.fayaz.todo_jc"
    const val designKit = "com.fayaz.todo_jc.design_kit"
    const val featureOnboarding = "com.fayaz.todo_jc.features.onboarding"
    const val utilsAndroidTest = "com.fayaz.todo_jc.utils.android_test"
    const val core = "com.fayaz.todo_jc.core"
    const val dashboard = "com.fayaz.todo_jc.features.dashboard"
  }
}
