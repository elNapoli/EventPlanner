package com.baldomeronapoli.eventplanner.mappers

interface Mappable<T> {
    fun map(): T
}

interface BiMappable<T, R> {
    fun mapToDto(): T
    fun mapToUI(): R
}