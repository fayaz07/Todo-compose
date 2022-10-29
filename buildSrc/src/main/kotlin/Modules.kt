object Modules {
  const val designKit = ":design-kit"
  const val core = ":core"
  const val domain = ":domain"

  object utils {
    private const val utils = ":utils"
    const val androidTest = utils + ":android-test"
  }

  object feature {
    private const val feature = ":features"
    const val onboarding = feature + ":onboarding"
    const val dashboard = feature + ":dashboard"
  }

  object data {
    private const val data = ":data"
    const val prefs = data + ":prefs"
    const val repository = data + ":repository"
  }

  object namespaces {
    const val appId = "com.fayaz.todo_jc"
    const val designKit = "com.fayaz.todo_jc.design_kit"
    const val featureOnboarding = "com.fayaz.todo_jc.features.onboarding"
    const val utilsAndroidTest = "com.fayaz.todo_jc.utils.android_test"
    const val core = "com.fayaz.todo_jc.core"
    const val dashboard = "com.fayaz.todo_jc.features.dashboard"
    const val prefs = "com.fayaz.todo_jc.data.prefs"
    const val repository = "com.fayaz.todo_jc.data.repository"
    const val domain = "com.fayaz.todo_jc.domain"
  }
}
