package com.baldomeronapoli.eventplanner.mappers

interface Mappable<T> {
    fun map(): T
}