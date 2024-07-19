package com.baldomeronapoli.eventplanner.utils

import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import kotlin.reflect.KClass

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ValidateState<State : Any>(kClass: KClass<State>) {
    fun validate(state: State): ValidationError?
}