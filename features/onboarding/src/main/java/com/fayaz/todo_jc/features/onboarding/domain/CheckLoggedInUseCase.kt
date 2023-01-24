package com.fayaz.todo_jc.features.onboarding.domain

import com.fayaz.todo_jc.domain.data.prefs.PrefsRepo
import com.fayaz.todo_jc.domain.data.usecase.BaseUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class CheckLoggedInUseCase constructor(
  private val prefsRepo: PrefsRepo
) : BaseUseCase() {
  operator fun invoke(): Flow<Boolean> {
    return prefsRepo.isLoggedIn()
  }

  suspend fun once(): Boolean {
    return invoke().first()
  }
}
