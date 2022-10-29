package com.fayaz.todo_jc.data.usecase

import com.fayaz.todo_jc.domain.data.prefs.PrefsRepo
import com.fayaz.todo_jc.domain.data.usecase.BaseUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.lastOrNull

class CheckLoggedInUseCase @Inject constructor(
  private val prefsRepo: PrefsRepo
) : BaseUseCase() {
  operator fun invoke(): Flow<Boolean> {
    return prefsRepo.isLoggedIn()
  }

  suspend fun once(): Boolean {
    return prefsRepo.isLoggedIn().lastOrNull() ?: false
  }
}
