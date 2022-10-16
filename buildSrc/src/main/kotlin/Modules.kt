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
  }
}
