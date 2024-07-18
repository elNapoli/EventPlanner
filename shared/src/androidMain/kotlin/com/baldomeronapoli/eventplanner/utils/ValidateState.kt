package com.baldomeronapoli.eventplanner.utils

import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import com.baldomeronapoli.eventplanner.domain.properties.EmailValidation
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ValidateState<State : Any> actual constructor(
    val kClass: KClass<State>
) {
    actual fun validate(state: State): ValidationError? {
        kClass.memberProperties.forEach {
            if (it.annotations.isEmpty())
                return@forEach

            val annotation = it.annotations[0]
            val property = it.name
            val value = it.get(state)

            if (annotation is EmailValidation) {
                if (isNotEmail(value)) {
                    return ValidationError(property, "Email no valido")
                }

            }

        }

        return null
    }
}

private fun isNotEmail(value: Any?): Boolean {
    return !Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        .matches(value.toString())
}