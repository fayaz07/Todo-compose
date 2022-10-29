package com.fayaz.todo_jc.domain.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppDispatchers(val dispatcher: TodoAppDispatchers)

enum class TodoAppDispatchers {
  IO,
  MAIN
}
