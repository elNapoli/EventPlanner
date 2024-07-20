package com.baldomeronapoli.eventplanner.domain.properties

@Target(AnnotationTarget.PROPERTY)
annotation class EqualsValidation(
    val otherProperty: String,
)