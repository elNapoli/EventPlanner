package com.baldomeronapoli.eventplanner.utils

import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import kotlin.reflect.KClass

expect class ValidateState<State : Any>(kClass: KClass<State>) {
    fun validate(state: State): ValidationError?
}