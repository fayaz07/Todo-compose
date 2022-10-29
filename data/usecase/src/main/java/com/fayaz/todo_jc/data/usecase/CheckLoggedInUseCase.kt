package com.fayaz.todo_jc.data.usecase

import com.fayaz.todo_jc.domain.data.prefs.PrefsRepo
import com.fayaz.todo_jc.domain.data.usecase.BaseUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single

class CheckLoggedInUseCase @Inject constructor(
  private val prefsRepo: PrefsRepo
) : BaseUseCase() {
  operator fun invoke(): Flow<Boolean> {
    return prefsRepo.isLoggedIn()
  }

  suspend fun single(): Boolean {
    return prefsRepo.isLoggedIn().single()
  }
}
