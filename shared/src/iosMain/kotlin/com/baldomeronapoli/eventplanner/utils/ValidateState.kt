package com.baldomeronapoli.eventplanner.utils

import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import kotlin.reflect.KClass

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ValidateState<State : Any> actual constructor(
    val kClass: KClass<State>
) {
    actual fun validate(state: State): ValidationError? {
       

        return null
    }
}